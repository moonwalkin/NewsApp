package com.example.newsapp.presentation.viewmodels

import androidx.lifecycle.*
import com.example.newsapp.domain.Result
import com.example.newsapp.domain.usecases.GetNewsUseCase
import com.example.newsapp.presentation.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsViewModel @Inject constructor(
    private val getNewsUseCase: GetNewsUseCase
) : BaseViewModel() {

    init {
        fetch()
    }

    private fun fetch() = viewModelScope.launch {
        _state.value = Result.Loading()
        _state.value = getNewsUseCase()
    }


}
