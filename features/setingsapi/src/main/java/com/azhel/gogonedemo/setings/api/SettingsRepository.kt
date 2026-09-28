package com.azhel.gogonedemo.setings.api

import kotlinx.coroutines.flow.StateFlow

interface SettingsRepository {

    fun getTheme(): StateFlow<AppTheme>

    fun saveTheme(appTheme: AppTheme)

    val showAnswerHistoryHint: StateFlow<Boolean>
    fun setShowAnswerHistoryHintEnabled(value: Boolean)

    val showStatisticsHint: StateFlow<Boolean>
    fun setShowStatisticsHintEnabled(value: Boolean)

    val showVerbLearningInfoStatisticScreen: StateFlow<Boolean>
    fun setShowVerbLearningInfoStatisticScreenEnabled(value: Boolean)

    val showVerbLearningInfoTestResultScreen: StateFlow<Boolean>
    fun setShowVerbLearningInfoTestResultScreenEnabled(value: Boolean)

    val showVerbsListScreenHint: StateFlow<Boolean>
    fun setShowVerbsListScreenHint(value: Boolean)

    val showVerbsListSetUpScreenHint: StateFlow<Boolean>
    fun setShowVerbsListSetUpScreenHint(value: Boolean)
}