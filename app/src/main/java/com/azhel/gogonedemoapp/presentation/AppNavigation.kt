package com.azhel.gogonedemoapp.presentation

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.runtime.result.rememberResultEventBusNavEntryDecorator
import androidx.navigation3.scene.DialogSceneStrategy
import androidx.navigation3.ui.NavDisplay
import com.azhel.gogonedemo.R
import com.azhel.gogonedemo.settings.R as SETTINGS_R
import com.azhel.gogonedemo.apiroutes.TopLevelVerbsRoute
import com.azhel.gogonedemo.designsystem.presentation.icons.AppIcons
import com.azhel.gogonedemo.navigation.Navigator
import com.azhel.gogonedemo.navigation.TopLevelRoute
import com.azhel.gogonedemo.presentation.icons.FeatureIcons
import com.azhel.gogonedemo.presentation.verbsFeatureNavBuilder
import com.azhel.gogonedemo.settings.apiroutes.TopLevelSettingsRoutes
import com.azhel.gogonedemo.settings.presentation.icons.SettingsIcon
import com.azhel.gogonedemo.settings.presentation.settingsNavFeatureBuilder
import org.koin.compose.koinInject
import org.koin.core.parameter.parametersOf

@Composable
fun AppNavigation() {

    val navigator = koinInject<Navigator>(
        parameters = { parametersOf(TopLevelVerbsRoute.VerbsList) }
    )

    val isMainScreenOpened by remember {
        derivedStateOf { navigator.backStack.last() is TopLevelVerbsRoute
                && navigator.backStack.last() != TopLevelSettingsRoutes.Settings
        }
    }

    Scaffold(
        bottomBar = {
            if (isMainScreenOpened) {

                NavigationBar {
                    listOf(
                        NavBottomButton(
                            TopLevelVerbsRoute.VerbsList, AppIcons.List, R.string.verbs_list
                        ),
                        NavBottomButton(
                            TopLevelVerbsRoute.Learn, FeatureIcons.Learn, R.string.learn
                        ),
                        NavBottomButton(
                            TopLevelSettingsRoutes.Settings, SettingsIcon, SETTINGS_R.string.settings_title
                        ))
                        .forEach { screen ->
                            val selected = screen.topLevelVerbsRoute == navigator.backStack.last()
                            val text = stringResource(screen.text)
                            NavigationBarItem(
                                icon = {
                                    Icon(
                                        imageVector = screen.icon,
                                        contentDescription = text,
                                        tint = if (selected) {
                                            MaterialTheme.colorScheme.primary
                                        } else {
                                            MaterialTheme.colorScheme.onSurfaceVariant
                                        }
                                    )
                                       },
                                label = {
                                    Text(
                                        text = text,
                                        textAlign = TextAlign.Center,
                                        modifier = Modifier.fillMaxWidth(),
                                        maxLines = 2
                                    )
                                },
                                selected = selected,
                                onClick = {
                                    navigator.addTopLevel(screen.topLevelVerbsRoute)
                                }
                            )
                        }
                }
            }
        }
    ) { innerPadding ->

        val direction = LocalLayoutDirection.current
        NavDisplay(
            backStack = navigator.backStack,
            modifier = Modifier.padding(
                top = innerPadding.calculateTopPadding(),
                bottom = innerPadding.calculateBottomPadding(),
                start = innerPadding.calculateStartPadding(direction),
                end = innerPadding.calculateEndPadding(direction)
            ),
            entryProvider = entryProvider {
                verbsFeatureNavBuilder()
                settingsNavFeatureBuilder()
            },
            sceneStrategies = listOf(
                remember {
                    DialogSceneStrategy()
                }
            ),
            entryDecorators = listOf(
                rememberSaveableStateHolderNavEntryDecorator(),
                rememberViewModelStoreNavEntryDecorator(),
                rememberResultEventBusNavEntryDecorator()
            ),
            onBack = navigator::removeLast,
        )
    }
}

data class NavBottomButton(
    val topLevelVerbsRoute: TopLevelRoute,
    val icon: ImageVector,
    @field:StringRes val text: Int
)
