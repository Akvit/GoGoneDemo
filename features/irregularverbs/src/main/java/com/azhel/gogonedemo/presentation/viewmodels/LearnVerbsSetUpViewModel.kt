package com.azhel.gogonedemo.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.azhel.gogonedemo.analytic.Crashlytics
import com.azhel.gogonedemo.domain.LearningVerbsFilter
import com.azhel.gogonedemo.domain.ProcessSetup
import com.azhel.gogonedemo.domain.usecases.GetVerbsCountUseCase
import com.azhel.gogonedemo.presentation.LearnVerbsGroup
import com.azhel.gogonedemo.presentation.contracts.LearnVerbsSetUpIntent
import com.azhel.gogonedemo.presentation.contracts.LearnVerbsSetUpState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LearnVerbsSetUpViewModel(
    private val getVerbsCountUseCase: GetVerbsCountUseCase,
    private val crashlytics: Crashlytics,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val mState = MutableStateFlow(LearnVerbsSetUpState())
    val state: StateFlow<LearnVerbsSetUpState> = mState

    fun onIntent(intent: LearnVerbsSetUpIntent) {
        when (intent) {
            is LearnVerbsSetUpIntent.Load -> loadCounts()
            is LearnVerbsSetUpIntent.ToggleGroup -> updateToggleState(intent.group)
        }
    }

    private fun updateToggleState(groupId: LearnVerbsGroup) {
        when(groupId) {
            LearnVerbsGroup.YOUR_VERBS -> mState.update {
                it.copy(isYourVerbsExpanded = !it.isYourVerbsExpanded)
            }

            else -> {}
        }
    }

    private fun loadCounts() {
        mState.update { it.copy(isLoading = true, error = null) }
        viewModelScope.launch {

            val countOfAllDeff = getCount(
                ProcessSetup.VerbsUnderLearn(LearningVerbsFilter.ALL)
            )

            val countNotTestedDeff = getCount(
                ProcessSetup.VerbsUnderLearn(LearningVerbsFilter.NOT_TESTED)
            )

            val countNeedPractiseDeff = getCount(
                ProcessSetup.VerbsUnderLearn(LearningVerbsFilter.NEED_PRACTICE)
            )

            val countLearnedDeff = getCount(
                ProcessSetup.VerbsUnderLearn(LearningVerbsFilter.LEARNED)
            )

            mState.update {
                it.copy(
                    allVerbsUnderLearnCount = countOfAllDeff.await(),
                    notTestedVerbsUnderLearnCount = countNotTestedDeff.await(),
                    needPracticeVerbsUnderLearnCount = countNeedPractiseDeff.await(),
                    learnedVerbsUnderLearnCount = countLearnedDeff.await(),
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
}
