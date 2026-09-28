package com.azhel.gogonedemo.navigation

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation3.runtime.NavKey

interface Navigator {

    val topLevelRoute: NavKey

    val backStack: SnapshotStateList<NavKey>

    fun removeLast()

    fun add(key: NavKey, cleanPrevious: Int = 0)

    fun addTopLevel(key: TopLevelRoute)
}