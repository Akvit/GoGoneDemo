package com.azhel.gogonedemo.analytic

import org.koin.dsl.module

val analyticModule = module {

    single<Crashlytics> {
        CrashlyticsImpl()
    }

}