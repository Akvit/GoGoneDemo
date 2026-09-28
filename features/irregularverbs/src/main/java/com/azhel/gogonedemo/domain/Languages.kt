package com.azhel.gogonedemo.domain

private val supportedLanguages = setOf("ru", "be", "uk", "pl")

fun getSupportedLocale(locale: String): String =
    if (locale in supportedLanguages) locale else "ru"