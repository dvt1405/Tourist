package com.kt.apps.media.taipeitour.data.settings

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LanguageSettings @Inject constructor(
    @ApplicationContext
    private val context: Context
) {
    private val _sharedPreferences by lazy {
        context.getSharedPreferences("language_settings", Context.MODE_PRIVATE)
    }

    fun getLanguage(): String {
        return _sharedPreferences.getString("language", "en") ?: "en"
    }

    fun setLanguage(language: String) {
        _sharedPreferences.edit().putString("language", language).apply()
    }
}