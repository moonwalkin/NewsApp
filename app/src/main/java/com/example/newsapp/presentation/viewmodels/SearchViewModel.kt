package com.example.newsapp.presentation.viewmodels

import androidx.lifecycle.viewModelScope
import com.example.newsapp.domain.Result
import com.example.newsapp.domain.usecases.GetNewsByQueryUseCase
import com.example.newsapp.presentation.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val getNewsByQueryUseCase: GetNewsByQueryUseCase
    ) : BaseViewModel() {

    fun fetch(query: String) {
        viewModelScope.launch {
            _state.postValue(Result.Loading())
            _state.postValue(getNewsByQueryUseCase(query))
        }
    }
}