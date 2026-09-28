package com.azhel.gogonedemo.settings.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.azhel.gogonedemo.setings.api.AppTheme
import com.azhel.gogonedemo.settings.R

@Composable
fun AppTheme.toDisplayName(): String {
    return when (this) {
        AppTheme.LIGHT -> stringResource(R.string.theme_light)
        AppTheme.DARK -> stringResource(R.string.theme_dark)
        AppTheme.SYSTEM -> stringResource(R.string.theme_system)
    }
}