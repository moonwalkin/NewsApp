package com.example.newsapp.data.mapper

import com.example.newsapp.data.NewsData
import com.example.newsapp.domain.models.NewsDomain

class NewsMapper : Mapper<NewsData, NewsDomain> {
    val mapper = ArticleMapper()
    override fun mapDataToDomain(toDomain: NewsData): NewsDomain {
        return NewsDomain(
            articles = toDomain.articles.map {
                mapper.mapDataToDomain(it)
            },
            status = toDomain.status,
            totalResults = toDomain.totalResults
        )
    }

    override fun mapDomainToData(toData: NewsDomain): NewsData {
        return NewsData(
            articles = toData.articles.map {
                mapper.mapDomainToData(it)
            },
            status = toData.status,
            totalResults = toData.totalResults
        )
    }
}