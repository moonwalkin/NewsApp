package com.example.newsapp.data.cloudsource

import androidx.lifecycle.LiveData
import com.example.newsapp.data.CacheDataSource
import com.example.newsapp.data.database.ArticleDao
import com.example.newsapp.data.models.ArticleData

class CacheDataSourceImpl(
    private val articleDao: ArticleDao
) : CacheDataSource {
    override fun getSavedNews(): LiveData<List<ArticleData>> {
        return articleDao.getAllSavedArticles()
    }

    override suspend fun save(article: ArticleData) {
        articleDao.save(article)
    }
}