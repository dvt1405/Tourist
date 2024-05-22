package com.kt.apps.media.taipeitour.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kt.apps.media.taipeitour.data.models.TourDTO
import com.kt.apps.media.taipeitour.data.repository.ITourRepository
import com.kt.apps.media.taipeitour.data.settings.LanguageSettings
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val _tourRepository: ITourRepository,
    private val _languageSettings: LanguageSettings
) : ViewModel() {
    private var currentPage: Int = 0
    private val _mainUIState by lazy {
        MutableLiveData<MainUIModel>()
    }

    val mainUIState: LiveData<MainUIModel>
        get() = _mainUIState

    fun onChangeLanguage(language: String) {
        _mainUIState.postValue(MainUIModel.Loading)
        _languageSettings.setLanguage(language)
    }

    fun getALlTour() {
        viewModelScope.launch(Dispatchers.IO) {
            _mainUIState.postValue(MainUIModel.Loading)
            currentPage = 0
            _tourRepository.getTourList()
                .collectLatest {
                    if (it.isSuccess) {
                        val data = it.getOrNull()
                        data?.let {
                            _mainUIState.postValue(MainUIModel.Success(it.tourList))
                            // do something with data
                        }
                    } else {
                        val error = it.exceptionOrNull()
                        error?.let {
                            _mainUIState.postValue(MainUIModel.Error(it))
                            // do something with error
                        }
                    }
                }
        }
    }

    fun nextTourPage() {
        viewModelScope.launch {
            currentPage++
            _tourRepository.getTourByPage(currentPage)
        }
    }

    fun getDetailTour(tour: TourDTO) {

    }
}