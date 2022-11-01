package com.example.newsapp.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.cloudsource.CloudDataSourceImpl
import com.example.newsapp.data.network.NewsFactory
import com.example.newsapp.data.repository.NewsRepositoryImpl
import com.example.newsapp.domain.GetNewsUseCase
import com.example.newsapp.domain.entities.NewsDomain
import kotlinx.coroutines.launch

class NewsViewModel : ViewModel() {
    private val service = NewsFactory.retrofit
    private val cloudDataSource = CloudDataSourceImpl(service)
    private val repository = NewsRepositoryImpl(cloudDataSource)
    private val useCase = GetNewsUseCase(repository)

    private val _news = MutableLiveData<NewsDomain>()
    val news: LiveData<NewsDomain>
        get() = _news


    init {
        fetch()
    }

    private fun fetch() = viewModelScope.launch {
        _news.postValue(useCase())
    }
}