package com.example.newsapp.data.mapper

import com.example.newsapp.data.models.ArticleData
import com.example.newsapp.domain.entities.ArticleDomain
import com.example.newsapp.domain.entities.SourceDomain

class Mapperr {
    fun mapToDomain(data: ArticleData): ArticleDomain {
        return ArticleDomain(
            author = data.author,
            content = data.description,
            description = data.description,
            publishedAt = data.publishedAt,
            source = SourceDomain(data.source!!.id, data.source.name),
            title = data.title,
            url = data.url,
            urlToImage = data.urlToImage
        )

    }
}