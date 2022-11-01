package com.example.newsapp.domain

import androidx.lifecycle.LiveData
import com.example.newsapp.domain.entities.ArticleDomain
import com.example.newsapp.domain.entities.NewsDomain

interface NewsRepository {

    suspend fun fetchNews(): NewsDomain

    fun getSavedNews(): LiveData<List<ArticleDomain>>

    suspend fun save(articleDomain: ArticleDomain)
}