package com.azhel.gogonedemo.settings.data

import com.azhel.gogonedemo.setings.api.AppTheme
import com.azhel.gogonedemo.setings.api.SettingsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SettingsRepositoryImpl(private val preferences: com.azhel.gogonedemo.settings.data.SettingsPreferences): SettingsRepository {

    private val _theme = MutableStateFlow(
        preferences.getTheme()
    )

    override fun getTheme(): StateFlow<AppTheme> {
        return _theme
    }

    override fun saveTheme(appTheme: AppTheme) {
        preferences.saveTheme(appTheme)
        _theme.update { appTheme }
    }

    private val _showAnswerHistoryHint =
        MutableStateFlow(preferences.showAnswerHistoryHint)

    override val showAnswerHistoryHint: StateFlow<Boolean> =
        _showAnswerHistoryHint.asStateFlow()

    override fun setShowAnswerHistoryHintEnabled(value: Boolean) {
        preferences.showAnswerHistoryHint = value
        _showAnswerHistoryHint.value = value
    }

    private val _showStatisticsHint =
        MutableStateFlow(preferences.showStatisticsHint)

    override val showStatisticsHint: StateFlow<Boolean> =
        _showStatisticsHint.asStateFlow()

    override fun setShowStatisticsHintEnabled(value: Boolean) {
        preferences.showStatisticsHint = value
        _showStatisticsHint.value = value
    }

    private val _showVerbLearningInfoStatisticScreen =
        MutableStateFlow(preferences.showVerbLearningInfoStatisticScreen)

    override val showVerbLearningInfoStatisticScreen: StateFlow<Boolean> =
        _showVerbLearningInfoStatisticScreen.asStateFlow()

    override fun setShowVerbLearningInfoStatisticScreenEnabled(value: Boolean) {
        preferences.showVerbLearningInfoStatisticScreen = value
        _showVerbLearningInfoStatisticScreen.value = value
    }

    private val _showVerbLearningInfoTestResultScreen =
        MutableStateFlow(preferences.showVerbLearningInfoTestResultScreen)

    override val showVerbLearningInfoTestResultScreen: StateFlow<Boolean> =
        _showVerbLearningInfoTestResultScreen.asStateFlow()

    override fun setShowVerbLearningInfoTestResultScreenEnabled(value: Boolean) {
        preferences.showVerbLearningInfoTestResultScreen = value
        _showVerbLearningInfoTestResultScreen.value = value
    }

    private val _showVerbsListScreenHint =
        MutableStateFlow(preferences.showVerbsListScreenHint)

    override val showVerbsListScreenHint: StateFlow<Boolean> =
        _showVerbsListScreenHint.asStateFlow()

    override fun setShowVerbsListScreenHint(value: Boolean) {
        preferences.showVerbsListScreenHint = value
        _showVerbsListScreenHint.value = value
    }

    private val _showVerbsListSetUpScreenHint =
        MutableStateFlow(preferences.showVerbsListSetUpScreenHint)

    override val showVerbsListSetUpScreenHint: StateFlow<Boolean> =
        _showVerbsListSetUpScreenHint.asStateFlow()

    override fun setShowVerbsListSetUpScreenHint(value: Boolean) {
        preferences.showVerbsListSetUpScreenHint = value
        _showVerbsListSetUpScreenHint.value = value
    }

}