package com.azhel.gogonedemo.presentation.contracts

import com.azhel.gogonedemo.presentation.LearnVerbsGroup

sealed interface LearnVerbsSetUpIntent {
    data object Load : LearnVerbsSetUpIntent
    data class ToggleGroup(val group: LearnVerbsGroup): LearnVerbsSetUpIntent
}

data class LearnVerbsSetUpState(
    val allVerbsUnderLearnCount: Int? = null,
    val notTestedVerbsUnderLearnCount: Int? = null,
    val needPracticeVerbsUnderLearnCount: Int? = null,
    val learnedVerbsUnderLearnCount: Int? = null,
    val isLoading: Boolean = true,
    val isYourVerbsExpanded: Boolean = false,
    val error: String? = null
)
