package com.kt.apps.media.taipeitour.repository

import com.kt.apps.media.taipeitour.data.API
import com.kt.apps.media.taipeitour.data.models.TourResponseDTO
import com.kt.apps.media.taipeitour.data.repository.ITourRepository
import com.kt.apps.media.taipeitour.data.settings.LanguageSettings
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.retry
import javax.inject.Inject

class TourRepositoryImpl @Inject constructor(
    private val _api: API,
    private val _languageSettings: LanguageSettings
) : ITourRepository {
    private var _currentPage: Int = 0
    override fun getTourList(): Flow<Result<TourResponseDTO>> = flow {
        val list = _api.getTourList(_languageSettings.getLanguage())
        emit(Result.success(list))
    }
        .catch {
            emit(Result.failure(it))
        }

    override fun getTourByPage(page: Int): Flow<Result<TourResponseDTO>> = flow {
        _currentPage = page
        val list = _api.getTourList(_languageSettings.getLanguage(), page)
        emit(Result.success(list))
    }
        .retry(3)
        .catch {
            emit(Result.failure(it))
        }
}