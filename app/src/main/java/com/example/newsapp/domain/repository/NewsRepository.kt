package com.example.newsapp.domain.repository

import androidx.lifecycle.LiveData
import com.example.newsapp.domain.Result
import com.example.newsapp.domain.entities.ArticleDomain
import com.example.newsapp.domain.entities.NewsDomain


interface NewsRepository {

    suspend fun fetchTopNews(): Result<NewsDomain>

    suspend fun fetchNewsByQuery(query: String): Result<NewsDomain>

    fun getSavedNews(): LiveData<List<ArticleDomain>>

    suspend fun save(articleDomain: ArticleDomain)

    suspend fun delete(articleDomain: ArticleDomain)
}