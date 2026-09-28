package com.azhel.gogonedemo.analytic

import timber.log.Timber

internal class CrashlyticsImpl: Crashlytics {

    override fun recordException(t: Throwable) {
        Timber.e(t)
    }
}