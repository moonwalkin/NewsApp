package com.example.newsapp.data.cloud

import com.example.newsapp.data.models.NewsData
import com.example.newsapp.data.network.NewsService
import javax.inject.Inject

class CloudDataSourceImpl @Inject constructor(
    private val apiService: NewsService
) : CloudDataSource {
    override suspend fun fetch(): NewsData {
        return apiService.fetch()
    }
}