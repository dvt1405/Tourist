package com.kt.apps.media.taipeitour

import android.app.Application
import android.util.Log
import com.kt.apps.media.taipeitour.data.settings.LanguageSettings
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import java.util.Locale
import javax.inject.Inject

@HiltAndroidApp
class App : Application() {

    @Inject
    lateinit var languageSettings: LanguageSettings

    override fun onCreate() {
        super.onCreate()
        if (languageSettings.getLanguage().isEmpty()) {
            val local = Locale.getDefault()
            if (Constants.supportedLanguages.contains(local.language.lowercase())) {
                languageSettings.setLanguage(local.language)
            } else {
                languageSettings.setLanguage("vi")
            }
        }
    }
}