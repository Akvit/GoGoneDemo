package com.azhel.gogonedemo.presentation.contracts

import androidx.compose.runtime.Stable
import kotlinx.collections.immutable.ImmutableList
import com.azhel.gogonedemo.domain.ProcessSetup
import com.azhel.gogonedemo.domain.models.VerbModel
import kotlinx.collections.immutable.ImmutableMap
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.persistentMapOf

sealed interface VerbsListIntent {
    data class Load(val local: String, val processSetup: ProcessSetup) : VerbsListIntent
    data class ToggleLearning(val verb: VerbModelUiState) : VerbsListIntent
    data object SaveChanges : VerbsListIntent
    data object HideVerbsListScreenHint: VerbsListIntent
    data class OnSearchTyping(val text: String): VerbsListIntent
}

sealed interface VerbsListEffect {
    data object NavigateBack : VerbsListEffect
}

data class VerbsListState(
    val listOfFound: ImmutableList<VerbModelUiState> = persistentListOf(),
    val query: String = "",
    val error: String? = null,
    val pendingLearningChanges: ImmutableMap<Int, Boolean> = persistentMapOf(),
    val isLoading: Boolean = true,
    val isShowVerbsListScreenHint: Boolean = true
)

data class VerbModelUiState(
    val verbModel: VerbModel,
    val learningVisualState: LearningVisualState
)

@Stable
sealed interface LearningVisualState {
    data object AlreadySelected : LearningVisualState
    data object NotSelected : LearningVisualState
    data object JustRemoved : LearningVisualState
    data object JustAdded: LearningVisualState
}
