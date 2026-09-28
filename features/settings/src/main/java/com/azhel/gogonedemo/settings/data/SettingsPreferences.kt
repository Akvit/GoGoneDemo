package com.azhel.gogonedemo.settings.data

import android.content.Context
import androidx.core.content.edit
import com.azhel.gogonedemo.setings.api.AppTheme

class SettingsPreferences(context: Context) {

    private val preferences = context.getSharedPreferences(
        "app_preferences",
        Context.MODE_PRIVATE
    )

    fun getTheme(): AppTheme {
        val value = preferences.getString(
            KEY_THEME,
            AppTheme.SYSTEM.name
        )

        return runCatching {
            AppTheme.valueOf(value ?: AppTheme.SYSTEM.name)
        }.getOrDefault(AppTheme.SYSTEM)
    }

    fun saveTheme(theme: AppTheme) {
        preferences.edit {
            putString(KEY_THEME, theme.name)
        }
    }

    var showAnswerHistoryHint: Boolean
        get() = preferences.getBoolean(KEY_SHOW_ANSWER_HISTORY_HINT, true)
        set(value) = preferences.edit {
            putBoolean(KEY_SHOW_ANSWER_HISTORY_HINT, value)
        }

    var showStatisticsHint: Boolean
        get() = preferences.getBoolean(KEY_SHOW_STATISTICS_HINT, true)
        set(value) = preferences.edit {
            putBoolean(KEY_SHOW_STATISTICS_HINT, value)
        }

    var showVerbLearningInfoStatisticScreen: Boolean
        get() = preferences.getBoolean(
            KEY_SHOW_VERB_LEARNING_INFO_STATISTIC_SCREEN,
            true
        )
        set(value) = preferences.edit {
            putBoolean(KEY_SHOW_VERB_LEARNING_INFO_STATISTIC_SCREEN, value)
        }

    var showVerbLearningInfoTestResultScreen: Boolean
        get() = preferences.getBoolean(
            KEY_SHOW_VERB_LEARNING_INFO_TEST_RESULT_SCREEN,
            true
        )
        set(value) = preferences.edit {
            putBoolean(KEY_SHOW_VERB_LEARNING_INFO_TEST_RESULT_SCREEN, value)
        }

    var showVerbsListScreenHint: Boolean
        get() = preferences.getBoolean(KEY_SHOW_VERBS_LIST_SCREEN_HINT, true)
        set(value) = preferences.edit {
            putBoolean(KEY_SHOW_VERBS_LIST_SCREEN_HINT, value)
        }

    var showVerbsListSetUpScreenHint: Boolean
        get() = preferences.getBoolean(KEY_SHOW_VERBS_LIST_SETUP_SCREEN_HINT, true)
        set(value) = preferences.edit {
            putBoolean(KEY_SHOW_VERBS_LIST_SETUP_SCREEN_HINT, value)
        }

    private companion object {
        const val KEY_SHOW_ANSWER_HISTORY_HINT = "show_answer_history_hint"
        const val KEY_SHOW_STATISTICS_HINT = "show_statistics_hint"
        const val KEY_SHOW_VERB_LEARNING_INFO_STATISTIC_SCREEN = "show_verb_learning_info_statistic_screen"
        const val KEY_SHOW_VERB_LEARNING_INFO_TEST_RESULT_SCREEN = "show_verb_learning_info_test_result_screen"
        const val KEY_SHOW_VERBS_LIST_SCREEN_HINT = "show_verbs_list_screen_hint"
        const val KEY_SHOW_VERBS_LIST_SETUP_SCREEN_HINT = "show_verbs_list_setup_screen_hint"

        const val KEY_THEME = "theme"
    }
}