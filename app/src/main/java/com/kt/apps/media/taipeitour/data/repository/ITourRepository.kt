package com.kt.apps.media.taipeitour.data.repository

import com.kt.apps.media.taipeitour.data.models.TourResponseDTO
import kotlinx.coroutines.flow.Flow

interface ITourRepository {
    fun getTourList(): Flow<Result<TourResponseDTO>>

    fun getTourByPage(page: Int): Flow<Result<TourResponseDTO>>
}