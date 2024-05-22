package com.kt.apps.media.taipeitour.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kt.apps.media.taipeitour.data.repository.ITourRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val _tourRepository: ITourRepository
) : ViewModel() {
    private var currentPage: Int = 0

    init {
        Log.d("MainViewModel", "init: ")
    }
    fun getALlTour() {
        Log.d("MainViewModel", "getALlTour:")
        viewModelScope.launch(Dispatchers.IO) {
            currentPage = 0
            _tourRepository.getTourList()
                .collectLatest {
                    Log.d("MainViewModel", "getALlTour: $it")
                    if (it.isSuccess) {
                        val data = it.getOrNull()
                        data?.let {
                            // do something with data
                        }
                    } else {
                        val error = it.exceptionOrNull()
                        error?.let {
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
}