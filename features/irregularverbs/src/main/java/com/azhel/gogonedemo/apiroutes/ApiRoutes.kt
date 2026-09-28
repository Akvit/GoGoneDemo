package com.azhel.gogonedemo.apiroutes

import com.azhel.gogonedemo.navigation.TopLevelRoute
import kotlinx.serialization.Serializable

@Serializable
sealed interface TopLevelVerbsRoute : TopLevelRoute {

    @Serializable
    object VerbsList : TopLevelVerbsRoute

    @Serializable
    object Learn: TopLevelVerbsRoute

}
