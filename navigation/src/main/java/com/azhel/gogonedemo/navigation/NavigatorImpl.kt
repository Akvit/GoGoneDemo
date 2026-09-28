package com.azhel.gogonedemo.navigation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation3.runtime.NavKey

internal class NavigatorImpl(startRoute: TopLevelRoute): Navigator {

    private val topLevelStacks : LinkedHashMap<TopLevelRoute, SnapshotStateList<NavKey>> = linkedMapOf(
        startRoute to mutableStateListOf(startRoute)
    )

    override var topLevelRoute: NavKey by mutableStateOf(startRoute)

    override val backStack: SnapshotStateList<NavKey> = mutableStateListOf(startRoute)

    override fun addTopLevel(key: TopLevelRoute) {
        if (topLevelStacks[key] == null){
            topLevelStacks[key] = mutableStateListOf(key)
        } else {

            topLevelStacks.apply {
                remove(key)?.let {
                    val newSubStack = if (key == topLevelRoute) mutableStateListOf(it.first())
                    else it
                    put(key, newSubStack)
                }
            }
        }
        topLevelRoute = key
        updateBackStack()
    }

    override fun removeLast(){
        val removedKey = topLevelStacks[topLevelRoute]?.removeLastOrNull()
        topLevelStacks.remove(removedKey)
        topLevelRoute = topLevelStacks.keys.last()
        updateBackStack()
    }

    override fun add(key: NavKey, cleanPrevious: Int) {
        if (cleanPrevious > 0) {
            repeat(cleanPrevious) {
                val removedKey = topLevelStacks[topLevelRoute]?.removeLastOrNull()
                topLevelStacks.remove(removedKey)
            }
        }
        topLevelStacks[topLevelRoute]?.add(key)
        updateBackStack()
    }

    private fun updateBackStack() {
        backStack.clear()
        val flatteredList = topLevelStacks.flatMap { it.value }
        backStack.addAll(flatteredList)
    }

}
