package com.kt.apps.media.taipeitour

import java.util.Locale

object Constants {
    const val BASE_URL = "https://www.travel.taipei/"

    val supportedLanguages = listOf("zh-tw", "zh-cn", "en", "ja", "ko", "es", "id", "th", "vi")
    val supportedLanguagesCode = mapOf(
        "zh-tw" to Locale.TAIWAN.language,
        "zh-cn" to Locale.CHINESE.language,
        "en" to Locale.ENGLISH.language,
        "ja" to Locale.JAPAN.language,
        "ko" to Locale.KOREA.language,
        "es" to "es",
        "id" to "in",
        "th" to "th",
        "vi" to "vi"
    )
}