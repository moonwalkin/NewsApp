package com.example.newsapp.data.repository

import com.example.newsapp.data.CloudDataSource
import com.example.newsapp.data.mapper.NewsMapper
import com.example.newsapp.domain.NewsRepository
import com.example.newsapp.domain.models.NewsDomain

class NewsRepositoryImpl(
    private val cloudDataSource: CloudDataSource
) : NewsRepository {
    override suspend fun fetchNews(): NewsDomain {
        return NewsMapper().mapToDomain(cloudDataSource.fetch())
    }
}