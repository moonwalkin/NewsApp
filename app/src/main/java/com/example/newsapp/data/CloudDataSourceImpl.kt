package com.example.newsapp.data

import com.example.newsapp.data.network.NewsService
import com.example.newsapp.domain.models.NewsDomain

class CloudDataSourceImpl(
    private val apiService: NewsService
) : CloudDataSource {
    override suspend fun fetch(): NewsDomain {
        return apiService.fetch()
    }
}