package com.azhel.gogonedemo.navigation

import org.koin.dsl.module

val navModule = module {
    single<Navigator> { NavigatorImpl(get()) }
}