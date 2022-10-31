package com.example.newsapp.data.repository

import com.example.newsapp.data.CloudDataSource
import com.example.newsapp.data.mapper.ArticleMapper
import com.example.newsapp.data.mapper.NewsMapper
import com.example.newsapp.domain.NewsRepository
import com.example.newsapp.domain.models.NewsDomain

class NewsRepositoryImpl(
    private val cloudDataSource: CloudDataSource,
) : NewsRepository {
    val mapper = ArticleMapper()
    override suspend fun fetchNews(): NewsDomain {
        return NewsMapper().mapDataToDomain(cloudDataSource.fetch())
    }
}