package com.azhel.gogonedemo.presentation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.scene.DialogSceneStrategy
import com.azhel.gogonedemo.apiroutes.TopLevelVerbsRoute
import com.azhel.gogonedemo.presentation.screens.dialogs.BreakTestSureDialog
import com.azhel.gogonedemo.presentation.screens.dialogs.DeleteVerbFromLearnListSureDialog
import com.azhel.gogonedemo.presentation.screens.processes.LearnVerbsScreen
import com.azhel.gogonedemo.presentation.screens.topscreens.VerbsListSetUpScreen
import com.azhel.gogonedemo.presentation.screens.processes.VerbsUnderLearnScreen
import com.azhel.gogonedemo.presentation.screens.processes.VerbsListScreen
import com.azhel.gogonedemo.presentation.screens.resultscreens.LearningResultScreen
import com.azhel.gogonedemo.presentation.screens.topscreens.LearnVerbsSetUpScreen

fun EntryProviderScope<NavKey>.verbsFeatureNavBuilder() {

    entry<TopLevelVerbsRoute.VerbsList> {
        VerbsListSetUpScreen()
    }

    entry<InnerRoutes.VerbsList> { learnProcess ->
        VerbsListScreen(learnProcess)
    }

    entry<TopLevelVerbsRoute.Learn> {
        LearnVerbsSetUpScreen()
    }

    entry<InnerRoutes.VerbsListToLearn> {
        VerbsUnderLearnScreen()
    }

    entry<InnerRoutes.LearnProcess> { learnProcess ->
        LearnVerbsScreen(learnProcess)
    }

    entry<InnerRoutes.ExitTestSureDialog>(metadata = DialogSceneStrategy.dialog()) { exitTestSureDialog ->
        BreakTestSureDialog(exitTestSureDialog.titleText)
    }

    entry<InnerRoutes.DeleteVerbFromListSureDialog>(metadata = DialogSceneStrategy.dialog()) { deleteVerbFromListSureDialog ->
        DeleteVerbFromLearnListSureDialog(deleteVerbFromListSureDialog.titleText, deleteVerbFromListSureDialog.verb)
    }

    entry<InnerRoutes.LearnResult> { learnResult ->
        LearningResultScreen(learnResult.processSetup)
    }

}
