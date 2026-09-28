package com.azhel.gogonedemo.settings.di

import com.azhel.gogonedemo.setings.api.SettingsRepository
import com.azhel.gogonedemo.settings.data.SettingsPreferences
import com.azhel.gogonedemo.settings.data.SettingsRepositoryImpl
import com.azhel.gogonedemo.settings.presentation.SettingsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val settingsModule = module {

    single<SettingsPreferences> {
        SettingsPreferences(
            androidContext()
        )
    }

    single<SettingsRepository> {
        SettingsRepositoryImpl(get())
    }

    viewModel {
        SettingsViewModel(get())
    }
}