package com.example.newsapp.data.mapper

import com.example.newsapp.data.models.NewsData
import com.example.newsapp.domain.entities.NewsDomain
import javax.inject.Inject

class NewsMapper @Inject constructor(
    private val mapper: ArticleMapper
) {
    fun mapToDomain(data: NewsData): NewsDomain {
        return NewsDomain(
            articles = data.articles.map {
                mapper.mapDataToDomain(it)
            },
            status = data.status,
            totalResults = data.totalResults
        )
    }
}