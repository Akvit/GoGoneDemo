package com.azhel.gogonedemo.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.azhel.gogonedemo.analytic.Crashlytics
import com.azhel.gogonedemo.domain.ProcessSetup
import com.azhel.gogonedemo.domain.models.VerbModel
import com.azhel.gogonedemo.domain.usecases.GetVerbsUseCase
import com.azhel.gogonedemo.domain.usecases.UpdateVerbsToLearnUseCase
import com.azhel.gogonedemo.presentation.contracts.VerbsUnderLearnIntent
import com.azhel.gogonedemo.presentation.contracts.VerbsUnderLearnState
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class VerbsUnderLearnViewModel(
    private val getVerbsUseCase: GetVerbsUseCase,
    private val updateVerbsToLearnUseCase: UpdateVerbsToLearnUseCase,
    private val crashlytics: Crashlytics,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val mState = MutableStateFlow(VerbsUnderLearnState())
    val state: StateFlow<VerbsUnderLearnState> = mState

    fun onIntent(intent: VerbsUnderLearnIntent) {
        when (intent) {
            is VerbsUnderLearnIntent.Load -> load(intent.local, intent.processSetup)
            is VerbsUnderLearnIntent.Delete -> delete(intent.verb)
        }
    }

    private fun load(local: String, processSetup: ProcessSetup) {
        mState.update { it.copy(isLoading = true, errorText = null) }
        viewModelScope.launch(dispatcher) {
            getVerbsUseCase.execute(local, processSetup)
                .onSuccess { result ->
                    mState.update {
                        it.copy(
                            list = result.toImmutableList(),
                            isLoading = false
                        )
                    }
                }
                .onFailure { error ->
                    logError(error)
                    mState.update {
                        it.copy(
                            isLoading = false,
                            errorText = error.message.orEmpty()
                        )
                    }
                }
        }
    }

    private fun delete(verbModel: VerbModel) {
        viewModelScope.launch {
            updateVerbsToLearnUseCase.execute(mapOf(verbModel.id to false))
                .onFailure { error ->
                    logError(error)
                    mState.update { it.copy(errorText = error.message.orEmpty()) }
                }
                .onSuccess {
                    val newList = state.value.list.toMutableList()
                    newList.remove(verbModel)
                    mState.update { it.copy(list = newList.toImmutableList()) }
                }
        }
    }

    private fun logError(t: Throwable) {
        crashlytics.recordException(t)
    }
}
