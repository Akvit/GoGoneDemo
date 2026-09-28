package com.azhel.gogonedemo.presentation

import androidx.navigation3.runtime.NavKey
import com.azhel.gogonedemo.domain.ProcessSetup
import com.azhel.gogonedemo.domain.models.VerbModel
import kotlinx.serialization.Serializable

@Serializable
internal sealed interface InnerRoutes : NavKey {

    @Serializable
    data class VerbsList(val processSetup: ProcessSetup): InnerRoutes

    @Serializable
    data class LearnProcess(val processSetup: ProcessSetup): InnerRoutes

    @Serializable
    data object VerbsListToLearn: InnerRoutes

    @Serializable
    data class ExitTestSureDialog(val titleText: String): InnerRoutes

    @Serializable
    data class DeleteVerbFromListSureDialog(val titleText: String, val verb: VerbModel): InnerRoutes

    @Serializable
    data class LearnResult(val processSetup: ProcessSetup): InnerRoutes

}
