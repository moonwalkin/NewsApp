package com.example.newsapp.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.domain.Results
import com.example.newsapp.domain.entities.NewsDomain
import com.example.newsapp.domain.usecases.GetNewsByQueryUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchViewModel @Inject constructor(private val getNewsByQueryUseCase: GetNewsByQueryUseCase) :
    ViewModel() {

    private val _news = MutableLiveData<Results<NewsDomain>>()
    val news: LiveData<Results<NewsDomain>> = _news

    fun fetch(query: String) {
        viewModelScope.launch {
            _news.postValue(Results.Loading())
            _news.postValue(getNewsByQueryUseCase(query))
        }
    }
}