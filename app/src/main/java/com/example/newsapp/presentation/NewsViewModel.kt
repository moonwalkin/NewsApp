package com.example.newsapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.CloudDataSourceImpl
import com.example.newsapp.data.network.NewsFactory
import com.example.newsapp.data.repository.NewsRepositoryImpl
import com.example.newsapp.domain.NewsRepository
import com.example.newsapp.domain.models.NewsDomain
import kotlinx.coroutines.launch

class NewsViewModel : ViewModel() {
    val service = NewsFactory.retrofit
    val cloudDataSource = CloudDataSourceImpl(service)
    val repository = NewsRepositoryImpl(cloudDataSource)
    private val _news = MutableLiveData<NewsDomain>()
    val news: LiveData<NewsDomain> = _news


    init {
        fetch()
    }

    fun fetch() = viewModelScope.launch {
        _news.value = repository.fetchNews()
    }
}