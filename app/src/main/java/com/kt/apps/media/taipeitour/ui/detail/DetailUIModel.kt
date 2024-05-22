package com.kt.apps.media.taipeitour.ui.detail

import com.kt.apps.media.taipeitour.data.models.TourDTO

sealed class DetailUIModel {
    data class Loading(val item: TourDTO) : DetailUIModel()
    data class Success(val item: TourDTO) : DetailUIModel()
    data class Error(val item: TourDTO, val error: Throwable) : DetailUIModel()
}