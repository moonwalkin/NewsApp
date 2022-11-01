package com.example.newsapp.data

import com.example.newsapp.data.network.NewsService

class CloudDataSourceImpl(
    private val apiService: NewsService
) : CloudDataSource {
    override suspend fun fetch(): NewsData {
        return apiService.fetch()
    }
}