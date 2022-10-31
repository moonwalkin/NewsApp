package com.example.newsapp.domain

import com.example.newsapp.data.NewsData
import com.example.newsapp.domain.models.NewsDomain

interface NewsRepository {

    suspend fun fetchNews(): NewsDomain
}