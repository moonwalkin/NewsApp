package com.example.newsapp.data.cloud

import com.example.newsapp.data.models.NewsData
import com.example.newsapp.domain.Results

interface CloudDataSource {

    suspend fun fetch(): NewsData

    suspend fun fetchNewsByQuery(query: String): NewsData
}