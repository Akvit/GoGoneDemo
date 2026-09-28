package com.azhel.gogonedemo.settings.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.azhel.gogonedemo.designsystem.dp16
import com.azhel.gogonedemo.designsystem.dp24
import com.azhel.gogonedemo.designsystem.dp8
import com.azhel.gogonedemo.designsystem.uielements.NavTopAppBar
import com.azhel.gogonedemo.navigation.Navigator
import com.azhel.gogonedemo.setings.api.AppTheme
import com.azhel.gogonedemo.settings.R
import com.azhel.gogonedemo.settings.presentation.icons.ThemeIcon
import com.azhel.gogonedemo.settings.presentation.icons.Tips
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject

@Composable
fun SettingsScreen() {

    val viewModel: SettingsViewModel = koinViewModel()
    val state = viewModel.theme.collectAsStateWithLifecycle().value

    val snackBarHostState = remember { SnackbarHostState() }
    val navigation: Navigator = koinInject()
    val snackBarMessage = stringResource(R.string.all_hints_enabled)
    val composeScope = rememberCoroutineScope()


    SettingsLayout(
        appTheme = state,
        snackBarHostState = snackBarHostState,
        onThemeChanged = viewModel::changeTheme,
        onOnboardingClick = {
            viewModel.switchOnOnboarding()
            composeScope.launch {
                snackBarHostState.showSnackbar(snackBarMessage)
            }
        },
        onBackClick = navigation::removeLast
    )
}

@Composable
fun SettingsLayout(
    appTheme: AppTheme,
    snackBarHostState: SnackbarHostState,
    onThemeChanged: (AppTheme) -> Unit,
    onOnboardingClick: () -> Unit,
    onBackClick: () -> Unit
) {

    var showThemeDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            NavTopAppBar(
                title = stringResource(R.string.settings_title),
                onBackClick = onBackClick
            )
        },
        snackbarHost = {
            SnackbarHost(snackBarHostState)
        }

    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = dp16)
        ) {

            Text(
                text = stringResource(R.string.settings_title),
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(
                    top = dp24,
                    bottom = dp16
                )
            )

            SettingsItem(
                title = stringResource(R.string.theme),
                description = appTheme.toDisplayName(),
                icon = ThemeIcon,
                onClick = {
                    showThemeDialog = true
                }
            )

            SettingsItem(
                title = stringResource(R.string.show_all_tips),
                description = stringResource(R.string.show_all_tips),
                icon = Tips,
                onClick = { onOnboardingClick() }
            )

            Spacer(modifier = Modifier.weight(1f))

            HorizontalDivider(
                modifier = Modifier.padding(vertical = dp8),
                color = MaterialTheme.colorScheme.outlineVariant
            )
        }
    }

    if (showThemeDialog) {
        ThemeDialog(
            currentTheme = appTheme,
            onThemeSelected = {
                onThemeChanged(it)
                showThemeDialog = false
            },
            onDismiss = {
                showThemeDialog = false
            }
        )
    }

}

@Composable
private fun SettingsItem(title: String, description: String, icon: ImageVector, onClick: () -> Unit) {
    ListItem(
        leadingContent = {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        },
        headlineContent = {
            Text(title)
        },
        supportingContent = {
            Text(description)
        },
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
    )

}