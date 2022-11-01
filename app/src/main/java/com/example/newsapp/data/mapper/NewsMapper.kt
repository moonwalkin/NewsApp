package com.example.newsapp.data.mapper

import com.example.newsapp.data.models.NewsData
import com.example.newsapp.domain.entities.NewsDomain

class NewsMapper {
    val mapper = ArticleMapper()
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