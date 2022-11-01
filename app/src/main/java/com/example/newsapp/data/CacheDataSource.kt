package com.example.newsapp.data

import androidx.lifecycle.LiveData
import com.example.newsapp.data.models.ArticleData

interface CacheDataSource {
    fun getSavedNews(): LiveData<List<ArticleData>>

    suspend fun save(article: ArticleData)

    suspend fun delete(article: ArticleData)
}