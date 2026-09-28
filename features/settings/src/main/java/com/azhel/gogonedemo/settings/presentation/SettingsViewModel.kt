package com.azhel.gogonedemo.settings.presentation

import androidx.lifecycle.ViewModel
import com.azhel.gogonedemo.setings.api.AppTheme
import com.azhel.gogonedemo.setings.api.SettingsRepository
import kotlinx.coroutines.flow.StateFlow

class SettingsViewModel(private val repository: SettingsRepository): ViewModel() {

    val theme: StateFlow<AppTheme> = repository.getTheme()

    fun switchOnOnboarding() {
        repository.setShowAnswerHistoryHintEnabled(true)
        repository.setShowStatisticsHintEnabled(true)
        repository.setShowVerbLearningInfoStatisticScreenEnabled(true)
        repository.setShowVerbLearningInfoTestResultScreenEnabled(true)
        repository.setShowVerbsListScreenHint(true)
        repository.setShowVerbsListSetUpScreenHint(true)
    }

    fun changeTheme(theme: AppTheme) {
        repository.saveTheme(theme)
    }

}