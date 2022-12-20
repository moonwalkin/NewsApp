package com.example.newsapp.data.cache

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.newsapp.data.database.ArticleDao
import com.example.newsapp.data.models.ArticleData
import javax.inject.Inject

class CacheDataSourceImpl @Inject constructor(
    private val articleDao: ArticleDao
) : CacheDataSource {

    override fun getSavedNews(): LiveData<List<ArticleData>> {
        return articleDao.getAllSavedArticles()
    }

    override suspend fun save(article: ArticleData) {
        articleDao.save(article)
    }

    override suspend fun delete(article: ArticleData) {
        articleDao.delete(article)
    }
}