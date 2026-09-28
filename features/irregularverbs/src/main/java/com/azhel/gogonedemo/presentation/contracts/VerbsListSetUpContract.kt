package com.azhel.gogonedemo.presentation.contracts

import com.azhel.gogonedemo.presentation.LearnVerbsGroup

sealed interface VerbsListSetUpIntent {
    data object Load : VerbsListSetUpIntent
    data object HideVerbsListSetUpScreenHint: VerbsListSetUpIntent
    data class ToggleGroup(val group: LearnVerbsGroup): VerbsListSetUpIntent
}

data class VerbsListSetUpState(
    val allVerbsCount: Int? = null,
    val verbsUnderLearnCount: Int? = null,
    val highUsedVerbsCount: Int? = null,
    val midUsedVerbsCount: Int? = null,
    val lowUserVerbsCount: Int? = null,
    val aaaTypeVerbsCount: Int? = null,
    val abbTypeVerbsCount: Int? = null,
    val abaTypeVerbsCount: Int? = null,
    val abcTypeVerbsCount: Int? = null,
    val isLoading: Boolean = true,
    val error: String? = null,
    val isShowVerbsListSetUpScreenHint: Boolean = true,
    val isPatternGroupExpanded: Boolean = false,
    val isFrequencyGroupExpanded: Boolean = false
)