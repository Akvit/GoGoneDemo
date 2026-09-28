package com.azhel.gogonedemo.settings.apiroutes

import com.azhel.gogonedemo.navigation.TopLevelRoute
import kotlinx.serialization.Serializable

@Serializable
sealed interface TopLevelSettingsRoutes: TopLevelRoute {

    @Serializable
    object Settings: TopLevelSettingsRoutes
}