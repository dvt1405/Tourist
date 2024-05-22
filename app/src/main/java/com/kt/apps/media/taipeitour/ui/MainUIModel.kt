package com.kt.apps.media.taipeitour.ui

import com.kt.apps.media.taipeitour.data.models.TourDTO

sealed class MainUIModel {
    data object Loading : MainUIModel()
    data class Success(val data: List<TourDTO>) : MainUIModel()
    data class Error(val error: Throwable) : MainUIModel()
}