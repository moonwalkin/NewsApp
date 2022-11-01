package com.example.newsapp.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.newsapp.data.cloudsource.CacheDataSourceImpl
import com.example.newsapp.data.cloudsource.CloudDataSourceImpl
import com.example.newsapp.data.database.SavedArticleDatabase
import com.example.newsapp.data.network.NewsFactory
import com.example.newsapp.data.repository.NewsRepositoryImpl
import com.example.newsapp.domain.entities.GetSavedNewsUseCase

class SavedNewsViewModel(application: Application) : AndroidViewModel(application) {
    private val service = NewsFactory.retrofit
    private val cloudDataSource = CloudDataSourceImpl(service)
    private val dao = SavedArticleDatabase.getInstance(application).articleDao()
    private val cacheDataSource = CacheDataSourceImpl(dao)
    private val repository = NewsRepositoryImpl(cloudDataSource, cacheDataSource)
    private val getSavedNewsUseCase = GetSavedNewsUseCase(repository)
    val _savedNews = getSavedNewsUseCase()


    fun delete() {

    }
}