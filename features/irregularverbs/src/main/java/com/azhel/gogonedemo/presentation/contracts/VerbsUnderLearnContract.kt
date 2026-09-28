package com.azhel.gogonedemo.presentation.contracts

import com.azhel.gogonedemo.domain.ProcessSetup
import com.azhel.gogonedemo.domain.models.VerbModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

sealed interface VerbsUnderLearnIntent {
    data class Load(val local: String, val processSetup: ProcessSetup) : VerbsUnderLearnIntent
    data class Delete(val verb: VerbModel) : VerbsUnderLearnIntent
}

data class VerbsUnderLearnState(
    val list: ImmutableList<VerbModel> = persistentListOf(),
    val errorText: String? = null,
    val isLoading: Boolean = true
)
