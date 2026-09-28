package com.azhel.gogonedemo.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.azhel.gogonedemo.domain.ProcessSetup
import com.azhel.gogonedemo.domain.VerbAnswerValidator
import com.azhel.gogonedemo.presentation.contracts.LearnUserAnswer
import com.azhel.gogonedemo.presentation.contracts.LearnVerbsIntent
import com.azhel.gogonedemo.presentation.contracts.LearningCardType
import com.azhel.gogonedemo.presentation.contracts.VerbLearningState
import com.azhel.gogonedemo.domain.usecases.GetVerbsUseCase
import com.azhel.gogonedemo.analytic.Crashlytics
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LearnVerbsViewModel(
    private val getVerbsUseCase: GetVerbsUseCase,
    private val validator: VerbAnswerValidator,
    private val crashlytics: Crashlytics,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val mState = MutableStateFlow(
        VerbLearningState()
    )
    val state: StateFlow<VerbLearningState> = mState

    fun onIntent(intent: LearnVerbsIntent) {
        when (intent) {
            is LearnVerbsIntent.Load -> {
                if (state.value.verbs.isEmpty()) {
                    load(intent.local, intent.processSetup)
                }
            }
            is LearnVerbsIntent.TypingInfinitive -> onInfinitiveTyping(intent.value)
            is LearnVerbsIntent.TypingPastSimple -> onPastSimpleTyping(intent.value)
            is LearnVerbsIntent.TypingPastParticiple -> onPastParticipleTyping(intent.value)
            is LearnVerbsIntent.FocusLostInfinitive -> onInfinitiveFocusLost()
            is LearnVerbsIntent.FocusLostPastSimple -> onPastSimpleFocusLost()
            is LearnVerbsIntent.FocusLostPastParticiple -> onPastParticipleFocusLost()
            is LearnVerbsIntent.NextCard -> onNextCard()
            is LearnVerbsIntent.PreviousCard -> onPreviousCard()
        }
    }

    private fun load(local: String, processSetup: ProcessSetup) {
        mState.update {
            it.copy(isLoading = true, errorMessage = null)
        }
        viewModelScope.launch(dispatcher) {
            getVerbsUseCase.execute(local, processSetup)
                .onSuccess { result ->
                    mState.update {
                        it.copy(
                            verbs = result.toImmutableList(),
                            isLoading = false
                        )
                    }
                }
                .onFailure { error ->
                    logError(error)
                    mState.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = error.message
                        )
                    }
                }
        }
    }

    private fun onInfinitiveTyping(value: String) {
        val newAnswer = state.value.answer.copy(infinitive = value)
        mState.update {
            it.copy(
                answer = newAnswer,
                infinitiveCorrect = null,
                canGoNext = validateNextButton(infinitiveAnswer = value)
            )
        }
    }

    private fun onPastSimpleTyping(value: String) {
        val newAnswer = state.value.answer.copy(pastSimple = value)
        mState.update {
            it.copy(
                answer = newAnswer,
                pastSimpleCorrect = null,
                canGoNext = validateNextButton(pastSimpleAnswer = value)
            )
        }
    }

    private fun onPastParticipleTyping(value: String) {
        val newAnswer = state.value.answer.copy(pastParticipate = value)
        mState.update {
            it.copy(
                answer = newAnswer,
                pastParticipleCorrect = null,
                canGoNext = validateNextButton(pastParticipateAnswer = value)
            )
        }
    }

    private fun onInfinitiveFocusLost() {
        validateInfinitive()
    }

    private fun onPastSimpleFocusLost() {
        validatePastSimple()
    }

    private fun onPastParticipleFocusLost() {
        validatePastParticiple()
    }

    private fun validateInfinitive() {
        val currentVerb = state.value.currentVerb ?: return
        val answer = state.value.answer.infinitive

        if (answer.isBlank()) return

        mState.update {
            it.copy(
                infinitiveCorrect = validator.isCorrect(
                    userAnswer = answer,
                    correctAnswer = currentVerb.infinitive
                )
            )
        }
    }

    private fun validatePastSimple() {
        val currentVerb = state.value.currentVerb ?: return
        val answer = state.value.answer.pastSimple

        if (answer.isBlank()) return

        mState.update {
            it.copy(
                pastSimpleCorrect = validator.isCorrect(
                    userAnswer = answer,
                    correctAnswer = currentVerb.pastSimple
                )
            )
        }
    }

    private fun validatePastParticiple() {
        val currentVerb = state.value.currentVerb ?: return
        val answer = state.value.answer.pastParticipate

        if (answer.isBlank()) return

        mState.update {
            it.copy(
                pastParticipleCorrect = validator.isCorrect(
                    userAnswer = answer,
                    correctAnswer = currentVerb.pastParticipate
                )
            )
        }
    }

    private fun validateNextButton(
        infinitiveAnswer: String = state.value.answer.infinitive,
        pastSimpleAnswer: String = state.value.answer.pastSimple,
        pastParticipateAnswer: String = state.value.answer.pastParticipate
    ): Boolean {
        if (state.value.verbs.isEmpty()) return false
        val currentVerb = state.value.currentVerb ?: return false
        if (infinitiveAnswer.isBlank() || pastSimpleAnswer.isBlank() || pastParticipateAnswer.isEmpty()) {
            return false
        }

        return validator.isCorrect(infinitiveAnswer, currentVerb.infinitive)
                && validator.isCorrect(pastSimpleAnswer, currentVerb.pastSimple)
                && validator.isCorrect(pastParticipateAnswer, currentVerb.pastParticipate)
    }

    private fun onNextCard() {
        if (!state.value.canGoNext) return

        when (state.value.cardType) {
            LearningCardType.SHOW -> {
                val nextType = LearningCardType.PRACTICE
                val resetAnswer = LearnUserAnswer()
                mState.update {
                    it.copy(
                        cardType = nextType,
                        answer = resetAnswer,
                        infinitiveCorrect = null,
                        pastSimpleCorrect = null,
                        pastParticipleCorrect = null,
                        canGoNext = false
                    )
                }
            }

            LearningCardType.PRACTICE -> {
                if (state.value.currentIndex == state.value.verbs.lastIndex) {
                    return
                }

                val nextIndex = state.value.currentIndex + 1
                val nextType = LearningCardType.SHOW
                val resetAnswer = LearnUserAnswer()
                mState.update {
                    it.copy(
                        currentIndex = nextIndex,
                        cardType = nextType,
                        answer = resetAnswer,
                        infinitiveCorrect = null,
                        pastSimpleCorrect = null,
                        pastParticipleCorrect = null,
                        canGoNext = true
                    )
                }
            }
        }
    }

    private fun onPreviousCard() {
        when (state.value.cardType) {
            LearningCardType.PRACTICE -> {
                val nextType = LearningCardType.SHOW
                val resetAnswer = LearnUserAnswer()
                mState.update {
                    it.copy(
                        cardType = nextType,
                        answer = resetAnswer,
                        infinitiveCorrect = null,
                        pastSimpleCorrect = null,
                        pastParticipleCorrect = null,
                        canGoNext = true
                    )
                }
            }

            LearningCardType.SHOW -> {
                if (state.value.currentIndex == 0) return

                val nextIndex = state.value.currentIndex - 1
                val nextType = LearningCardType.PRACTICE
                val resetAnswer = LearnUserAnswer()
                mState.update {
                    it.copy(
                        currentIndex = nextIndex,
                        cardType = nextType,
                        answer = resetAnswer,
                        infinitiveCorrect = null,
                        pastSimpleCorrect = null,
                        pastParticipleCorrect = null,
                        canGoNext = false
                    )
                }
            }
        }
    }

    private fun logError(t: Throwable) {
        crashlytics.recordException(t)
    }

}
