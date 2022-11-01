package com.example.newsapp.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.example.newsapp.data.cloudsource.CacheDataSourceImpl
import com.example.newsapp.data.cloudsource.CloudDataSourceImpl
import com.example.newsapp.data.database.SavedArticleDatabase
import com.example.newsapp.data.network.NewsFactory
import com.example.newsapp.data.repository.NewsRepositoryImpl
import com.example.newsapp.domain.GetNewsUseCase
import com.example.newsapp.domain.entities.ArticleDomain
import com.example.newsapp.domain.entities.NewsDomain
import com.example.newsapp.domain.entities.SaveArticleUseCase
import kotlinx.coroutines.launch

class NewsViewModel(application: Application) : AndroidViewModel(application) {
    private val service = NewsFactory.retrofit
    private val cloudDataSource = CloudDataSourceImpl(service)
    private val dao = SavedArticleDatabase.getInstance(application).articleDao()
    private val cacheDataSource = CacheDataSourceImpl(dao)
    private val repository = NewsRepositoryImpl(cloudDataSource, cacheDataSource)
    private val useCase = GetNewsUseCase(repository)
    private val saveNewsUseCase = SaveArticleUseCase(repository)

    private val _news = MutableLiveData<NewsDomain>()
    val news: LiveData<NewsDomain>
        get() = _news


    init {
        fetch()
    }

    private fun fetch() = viewModelScope.launch {
        _news.postValue(useCase())
    }

    fun save(article: ArticleDomain) {
        viewModelScope.launch {
            saveNewsUseCase(article)
        }
    }
}