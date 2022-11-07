package com.example.newsapp.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.domain.entities.NewsDomain
import com.example.newsapp.domain.usecases.GetNewsByQueryUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchViewModel @Inject constructor(private val getNewsByQueryUseCase: GetNewsByQueryUseCase) : ViewModel() {

    private val _news = MutableLiveData<NewsDomain>()
    val news: LiveData<NewsDomain> = _news

     fun fetch(query: String) {
         Log.d("TAG", "ASd")
        viewModelScope.launch {

                _news.postValue(getNewsByQueryUseCase(query))
        }
    }
}