package com.azhel.gogonedemoapp.presentation

import android.app.Application
import com.azhel.gogonedemoapp.BuildConfig
import com.azhel.gogonedemo.navigation.navModule
import com.azhel.gogonedemo.analytic.analyticModule
import com.azhel.gogonedemo.di.irregularVerbsModule
import com.azhel.gogonedemo.settings.di.settingsModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import timber.log.Timber

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(irregularVerbsModule, navModule, analyticModule, settingsModule)
        }
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

    }

}