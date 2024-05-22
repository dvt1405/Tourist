package com.kt.apps.media.taipeitour.utils

fun isInWhiteListLanguage(language: String): Boolean {
    return language == "zh" || language == "en" || language == "ja"
}