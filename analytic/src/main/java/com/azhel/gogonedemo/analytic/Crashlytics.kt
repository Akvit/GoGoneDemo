package com.azhel.gogonedemo.analytic

interface Crashlytics {

    fun recordException(t: Throwable)

}