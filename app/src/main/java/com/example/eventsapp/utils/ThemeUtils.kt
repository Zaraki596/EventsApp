package com.example.eventsapp.utils

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate

fun applyTheme(theme: Int) {
    AppCompatDelegate.setDefaultNightMode(theme)
}

/**
 * Returns if dark theme is active or not.
 */
fun AppCompatActivity.isDarkTheme(): Boolean {
    return (resources.configuration.uiMode and
            Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES)
}