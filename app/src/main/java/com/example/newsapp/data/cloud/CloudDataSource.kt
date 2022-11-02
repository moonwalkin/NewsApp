package com.example.newsapp.data.cloud

import com.example.newsapp.data.models.NewsData

interface CloudDataSource {

    suspend fun fetch(page: Int = 1, pageSize: Int = 20): NewsData
}