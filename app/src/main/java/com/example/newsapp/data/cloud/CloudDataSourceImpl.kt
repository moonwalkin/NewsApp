package com.example.newsapp.data.cloud

import androidx.paging.PagingData
import com.example.newsapp.data.models.NewsData
import com.example.newsapp.data.network.NewsService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CloudDataSourceImpl @Inject constructor(
    private val apiService: NewsService
) : CloudDataSource {
    override suspend fun fetch(): NewsData {
        return apiService.fetch()
    }
}