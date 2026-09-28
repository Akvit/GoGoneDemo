package com.azhel.gogonedemo.settings.presentation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.azhel.gogonedemo.settings.apiroutes.TopLevelSettingsRoutes

fun EntryProviderScope<NavKey>.settingsNavFeatureBuilder() {

    entry<TopLevelSettingsRoutes.Settings> {
        SettingsScreen()
    }
}