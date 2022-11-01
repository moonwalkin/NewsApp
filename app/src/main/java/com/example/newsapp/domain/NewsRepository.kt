package com.example.newsapp.domain

import com.example.newsapp.domain.entities.NewsDomain

interface NewsRepository {

    suspend fun fetchNews(): NewsDomain
}