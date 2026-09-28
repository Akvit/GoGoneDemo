package com.azhel.gogonedemo.settings.presentation

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import androidx.core.net.toUri

object UrlLauncher {

    fun openUrl(
        context: Context,
        url: String,
        onError: (Throwable) -> Unit
    ) {
        try {
            context.startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    url.toUri()
                )
            )
        } catch (t: ActivityNotFoundException) {
            onError(t)
        } catch (t: Exception) {
            onError(t)
        }
    }

    fun openEmail(
        context: Context,
        email: String,
        onError: (Throwable) -> Unit
    ) {
        try {
            context.startActivity(
                Intent(
                    Intent.ACTION_SENDTO,
                    "mailto:$email".toUri()
                )
            )
        } catch (t: ActivityNotFoundException) {
            onError(t)
        } catch (t: Exception) {
            onError(t)
        }
    }

    fun openLinkedIn(
        context: Context,
        profileUrl: String,
        onError: (Throwable) -> Unit
    ) {
        try {
            context.startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    profileUrl.toUri()
                ).apply {
                    setPackage("com.linkedin.android")
                }
            )
        } catch (_: ActivityNotFoundException) {
            openUrl(
                context = context,
                url = profileUrl,
                onError = onError
            )
        } catch (t: Exception) {
            onError(t)
        }
    }
}