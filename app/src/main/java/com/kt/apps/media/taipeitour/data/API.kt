package com.kt.apps.media.taipeitour.data

import com.kt.apps.media.taipeitour.data.models.TourResponseDTO
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface API {
    @GET("open-api/{lang}/Attractions/All")
    @Headers("accept: application/json")
    suspend fun getTourList(
        @Path("lang") lang: String,
        @Query("page") page: Int? = null,
        @Query("categoryIds") categoryIds: String? = null,
        @Query("nlat") nlat: Double? = null,
        @Query("elong") elong: Double? = null,
    ): TourResponseDTO
}