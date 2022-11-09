package com.example.newsapp.data.cloud

import com.example.newsapp.data.models.NewsData

interface CloudDataSource {

    suspend fun fetch(): NewsData

    suspend fun fetchNewsByQuery(query: String): NewsData
}