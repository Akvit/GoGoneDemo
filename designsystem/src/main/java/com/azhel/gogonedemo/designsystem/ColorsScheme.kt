package com.azhel.gogonedemo.designsystem

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

val LightColorScheme = lightColorScheme(
    primary = LightPrimaryAccent,
    onPrimary = Color.White,
    primaryContainer = LightPrimaryAccentSoft,
    onPrimaryContainer = LightPrimaryAccentPressed,

    secondary = LightSecondaryAccent,
    onSecondary = Color.White,
    secondaryContainer = LightPurpleSoft,
    onSecondaryContainer = LightSecondaryAccent,

    tertiary = LightInfo,
    onTertiary = Color.White,
    tertiaryContainer = LightInfoSoft,
    onTertiaryContainer = LightInfo,

    background = LightBackground,
    onBackground = LightTextPrimary,

    surface = LightSurface,
    onSurface = LightTextPrimary,

    surfaceVariant = LightSecondaryBackground,
    onSurfaceVariant = LightTextSecondary,

    outline = LightBorder,
    outlineVariant = LightBorderStrong,

    error = LightError,
    onError = Color.White,
    errorContainer = LightErrorSoft,
    onErrorContainer = LightError
)

val DarkColorScheme = darkColorScheme(
    primary = DarkPrimaryAccent,
    onPrimary = DarkBackground,
    primaryContainer = DarkPrimaryAccentSoft,
    onPrimaryContainer = DarkPrimaryAccentPressed,

    secondary = DarkSecondaryAccent,
    onSecondary = DarkBackground,
    secondaryContainer = DarkPurpleSoft,
    onSecondaryContainer = DarkSecondaryAccent,

    tertiary = DarkInfo,
    onTertiary = DarkBackground,
    tertiaryContainer = DarkInfoSoft,
    onTertiaryContainer = DarkInfo,

    background = DarkBackground,
    onBackground = DarkTextPrimary,

    surface = DarkSurface,
    onSurface = DarkTextPrimary,

    surfaceVariant = DarkSecondaryBackground,
    onSurfaceVariant = DarkTextSecondary,

    outline = DarkBorder,
    outlineVariant = DarkBorderStrong,

    error = DarkError,
    onError = DarkBackground,
    errorContainer = DarkErrorSoft,
    onErrorContainer = DarkError
)