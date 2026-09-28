package com.azhel.gogonedemo.presentation.screens.dialogs

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.result.LocalResultEventBus
import com.azhel.gogonedemo.designsystem.YesNoSureDialogLayout
import com.azhel.gogonedemo.navigation.Navigator
import com.azhel.gogonedemo.presentation.BreakTestEvent
import org.koin.compose.koinInject

@Composable
fun BreakTestSureDialog(titleText: String) {
    val navigator: Navigator = koinInject()
    val resultBus = LocalResultEventBus.current
    YesNoSureDialogLayout(
        titleText = titleText,
        onDismiss = navigator::removeLast,
        onConfirm = {
            navigator.removeLast()
            resultBus.sendResult<BreakTestEvent>(result = BreakTestEvent)
        }
    )
}
