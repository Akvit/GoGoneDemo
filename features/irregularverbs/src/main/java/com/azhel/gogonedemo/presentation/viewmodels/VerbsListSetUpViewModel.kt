package com.azhel.gogonedemo.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.azhel.gogonedemo.analytic.Crashlytics
import com.azhel.gogonedemo.domain.LearningVerbsFilter
import com.azhel.gogonedemo.domain.ProcessSetup
import com.azhel.gogonedemo.domain.VerbsFrequencyEnum
import com.azhel.gogonedemo.domain.VerbsPatternEnum
import com.azhel.gogonedemo.domain.usecases.GetVerbsCountUseCase
import com.azhel.gogonedemo.presentation.LearnVerbsGroup
import com.azhel.gogonedemo.presentation.contracts.VerbsListSetUpIntent
import com.azhel.gogonedemo.presentation.contracts.VerbsListSetUpState
import com.azhel.gogonedemo.setings.api.SettingsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class VerbsListSetUpViewModel(
    private val getVerbsCountUseCase: GetVerbsCountUseCase,
    private val crashlytics: Crashlytics,
    private val settingsRepository: SettingsRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val mState = MutableStateFlow(VerbsListSetUpState())
    val state: StateFlow<VerbsListSetUpState> = mState

    init {
        initObservers()
    }

    fun onIntent(intent: VerbsListSetUpIntent) {
        when (intent) {
            is VerbsListSetUpIntent.Load -> loadCounts()
            VerbsListSetUpIntent.HideVerbsListSetUpScreenHint -> {
                settingsRepository.setShowVerbsListSetUpScreenHint(false)
                mState.update { it.copy(isShowVerbsListSetUpScreenHint = false) }
            }

            is VerbsListSetUpIntent.ToggleGroup -> handleToggle(intent.group)
        }
    }

    private fun handleToggle(groupId: LearnVerbsGroup) {
        when(groupId) {
            LearnVerbsGroup.FREQUENCY -> {
                mState.update {
                    it.copy(isFrequencyGroupExpanded = !it.isFrequencyGroupExpanded)
                }
            }
            LearnVerbsGroup.PATTERN -> {
                mState.update {
                    it.copy(isPatternGroupExpanded = !it.isPatternGroupExpanded)
                }
            }

            else -> {}
        }
    }

    private fun loadCounts() {
        mState.update { it.copy(isLoading = true, error = null) }
        viewModelScope.launch {
            val allVerbsCountDeff = getCount(ProcessSetup.All)

            val verbsUnderLearnCountDeff = getCount(
                ProcessSetup.VerbsUnderLearn(LearningVerbsFilter.ALL)
            )

            val highUsedVerbsCountDeff = getCount(ProcessSetup.Frequency(VerbsFrequencyEnum.HIGH))

            val midUsedVerbsCountDeff = getCount(ProcessSetup.Frequency(VerbsFrequencyEnum.MEDIUM))

            val lowUserVerbsCountDeff = getCount(ProcessSetup.Frequency(VerbsFrequencyEnum.LOW))

            val aaaTypeVerbsCountDeff = getCount(ProcessSetup.Pattern(VerbsPatternEnum.AAA))

            val abbTypeVerbsCountDeff = getCount(ProcessSetup.Pattern(VerbsPatternEnum.ABB))

            val abaTypeVerbsCountDeff = getCount(ProcessSetup.Pattern(VerbsPatternEnum.ABA))

            val abcTypeVerbsCountDeff = getCount(ProcessSetup.Pattern(VerbsPatternEnum.ABC))

            mState.update {
                it.copy(
                    allVerbsCount = allVerbsCountDeff.await(),
                    verbsUnderLearnCount = verbsUnderLearnCountDeff.await(),
                    highUsedVerbsCount = highUsedVerbsCountDeff.await(),
                    midUsedVerbsCount = midUsedVerbsCountDeff.await(),
                    lowUserVerbsCount = lowUserVerbsCountDeff.await(),
                    aaaTypeVerbsCount = aaaTypeVerbsCountDeff.await(),
                    abbTypeVerbsCount = abbTypeVerbsCountDeff.await(),
                    abaTypeVerbsCount = abaTypeVerbsCountDeff.await(),
                    abcTypeVerbsCount = abcTypeVerbsCountDeff.await(),
                    isLoading = false
                )
            }
        }
    }

    private fun getCount(processSetup: ProcessSetup): Deferred<Int> {
        return viewModelScope.async(dispatcher) {
            getVerbsCountUseCase
                .execute(processSetup)
                .onFailure(::logError).getOrDefault(0)
        }
    }

    private fun logError(t: Throwable) {
        mState.update {
            it.copy(
                isLoading = false,
                error = t.message
            )
        }
        crashlytics.recordException(t)
    }

    private fun initObservers() {
        viewModelScope.launch(dispatcher) {
            settingsRepository.showVerbsListSetUpScreenHint.collect { collectValue ->
                mState.update { it.copy(isShowVerbsListSetUpScreenHint = collectValue) }
            }
        }
    }
}
