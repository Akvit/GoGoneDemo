package com.azhel.gogonedemo.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.azhel.gogonedemo.analytic.Crashlytics
import com.azhel.gogonedemo.domain.ProcessSetup
import com.azhel.gogonedemo.domain.usecases.GetVerbsUseCase
import com.azhel.gogonedemo.domain.usecases.UpdateVerbsToLearnUseCase
import com.azhel.gogonedemo.presentation.contracts.LearningVisualState
import com.azhel.gogonedemo.presentation.contracts.VerbModelUiState
import com.azhel.gogonedemo.presentation.contracts.VerbsListEffect
import com.azhel.gogonedemo.presentation.contracts.VerbsListIntent
import com.azhel.gogonedemo.presentation.contracts.VerbsListState
import com.azhel.gogonedemo.setings.api.SettingsRepository
import kotlinx.collections.immutable.persistentMapOf
import kotlinx.collections.immutable.toImmutableList
import kotlinx.collections.immutable.toImmutableMap
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class VerbsListViewModel(
    private val getVerbsUseCase: GetVerbsUseCase,
    private val updateVerbsToLearnUseCase: UpdateVerbsToLearnUseCase,
    private val crashlytics: Crashlytics,
    private val settingsRepository: SettingsRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private var cachedList = emptyList<VerbModelUiState>()

    private val mState = MutableStateFlow(VerbsListState())
    val state: StateFlow<VerbsListState> = mState

    private val mEffect = Channel<VerbsListEffect>()
    val effect = mEffect.receiveAsFlow()

    init {
        initObserver()
    }

    fun onIntent(intent: VerbsListIntent) {
        when (intent) {
            is VerbsListIntent.Load -> {
                if (state.value.listOfFound.isEmpty()) {
                    load(intent.local, intent.processSetup)
                }
            }
            is VerbsListIntent.ToggleLearning -> toggleLearning(intent.verb)
            is VerbsListIntent.SaveChanges -> saveChanges()
            VerbsListIntent.HideVerbsListScreenHint -> {
                settingsRepository.setShowVerbsListScreenHint(false)
                mState.update { it.copy(isShowVerbsListScreenHint = false) }
            }

            is VerbsListIntent.OnSearchTyping -> searchVerbs(intent.text)
        }
    }

    private fun searchVerbs(text: String) {
        val newList = if (text.isBlank()) cachedList
        else cachedList.filter {
            it.verbModel.infinitive.contains(text, ignoreCase = true) ||
                    it.verbModel.pastSimple.contains(text, ignoreCase = true) ||
                    it.verbModel.pastParticipate.contains(text, ignoreCase = true) ||
                    it.verbModel.translation.contains(text, ignoreCase = true)

        }

        mState.update {
            it.copy(
                query = text,
                listOfFound = newList.toImmutableList()
            )
        }
    }

    private fun initObserver() {
        viewModelScope.launch(dispatcher) {
            settingsRepository.showVerbsListScreenHint.collect { collectValue ->
                mState.update { it.copy(isShowVerbsListScreenHint = collectValue) }
            }
        }
    }

    private fun load(local: String, processSetup: ProcessSetup) {
        mState.update {
            it.copy(isLoading = true, error = null)
        }
        viewModelScope.launch(dispatcher) {
            getVerbsUseCase.execute(local, processSetup)
                .onSuccess { result ->
                    mState.update {
                        cachedList = result.map { result ->
                            VerbModelUiState(
                                result,
                                if (result.isLearning) LearningVisualState.AlreadySelected else LearningVisualState.NotSelected
                            )
                        }
                        it.copy(
                            listOfFound = cachedList.toImmutableList(),
                            isLoading = false
                        )
                    }
                }
                .onFailure { error ->
                    logError(error)
                    mState.update {
                        it.copy(
                            error = error.message.orEmpty(),
                            isLoading = false
                        )
                    }
                }
        }
    }

    private fun toggleLearning(verb: VerbModelUiState) {

        val pendingChanges = state.value.pendingLearningChanges.toMutableMap()
        if (verb.verbModel.id in pendingChanges) {
            pendingChanges.remove(verb.verbModel.id)
        } else {
            pendingChanges[verb.verbModel.id] = !verb.verbModel.isLearning
        }

        val updatedList = state.value.listOfFound.map { currentVerb ->
            if (currentVerb == verb) {
                val verbState = when(currentVerb.learningVisualState) {
                    LearningVisualState.AlreadySelected -> {
                        LearningVisualState.JustRemoved
                    }
                    LearningVisualState.JustAdded -> LearningVisualState.NotSelected
                    LearningVisualState.JustRemoved -> LearningVisualState.AlreadySelected
                    LearningVisualState.NotSelected -> LearningVisualState.JustAdded
                }
                currentVerb.copy(learningVisualState = verbState)
            } else {
                currentVerb
            }
        }.toImmutableList()

        mState.update {
            it.copy(
                listOfFound = updatedList,
                pendingLearningChanges = pendingChanges.toImmutableMap()
            )
        }
    }

    private fun saveChanges() {
        viewModelScope.launch(dispatcher) {
            updateVerbsToLearnUseCase.execute(state.value.pendingLearningChanges)
                .onFailure(::logError)
                .onSuccess {
                    mState.update {
                        it.copy(
                            pendingLearningChanges = persistentMapOf()
                        )
                    }
                    mEffect.send(VerbsListEffect.NavigateBack)
                }
        }
    }

    private fun logError(t: Throwable) {
        crashlytics.recordException(t)
    }

}
