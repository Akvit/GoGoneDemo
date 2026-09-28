package com.azhel.gogonedemo.presentation.contracts

import com.azhel.gogonedemo.domain.ProcessSetup
import com.azhel.gogonedemo.domain.models.VerbModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

import java.io.Serializable as JavaSerializable

sealed interface LearnVerbsIntent {
    data class Load(val local: String, val processSetup: ProcessSetup) : LearnVerbsIntent
    data class TypingInfinitive(val value: String) : LearnVerbsIntent
    data class TypingPastSimple(val value: String) : LearnVerbsIntent
    data class TypingPastParticiple(val value: String) : LearnVerbsIntent
    data object FocusLostInfinitive : LearnVerbsIntent
    data object FocusLostPastSimple : LearnVerbsIntent
    data object FocusLostPastParticiple : LearnVerbsIntent
    data object NextCard : LearnVerbsIntent
    data object PreviousCard : LearnVerbsIntent
}

enum class LearningCardType {
    SHOW,
    PRACTICE
}

data class LearnUserAnswer(
    val infinitive: String = "",
    val pastSimple: String = "",
    val pastParticipate: String = ""
) : JavaSerializable

data class VerbLearningState(
    val verbs: ImmutableList<VerbModel> = persistentListOf(),
    val currentIndex: Int = 0,
    val cardType: LearningCardType = LearningCardType.SHOW,
    val answer: LearnUserAnswer = LearnUserAnswer(),
    val infinitiveCorrect: Boolean? = null,
    val pastSimpleCorrect: Boolean? = null,
    val pastParticipleCorrect: Boolean? = null,
    val canGoNext: Boolean = cardType == LearningCardType.SHOW,
    val isLoading: Boolean = true,
    val errorMessage: String? = null
) {
    val currentVerb: VerbModel?
        get() = verbs.getOrNull(currentIndex)

    val totalVerbs: Int
        get() = verbs.size
}
