package com.example.newsapp.data.mapper

import com.example.newsapp.data.ArticleData
import com.example.newsapp.data.SourceData
import com.example.newsapp.domain.models.ArticleDomain
import com.example.newsapp.domain.models.SourceDomain

class ArticleMapper : Mapper<ArticleData, ArticleDomain> {
    override fun mapDataToDomain(toDomain: ArticleData): ArticleDomain {
        return ArticleDomain(
            author = toDomain.author,
            content = toDomain.description,
            description = toDomain.description,
            publishedAt = toDomain.publishedAt,
            source = SourceDomain(toDomain.source.id, toDomain.source.name),
            title = toDomain.title,
            url = toDomain.url,
            urlToImage = toDomain.urlToImage
        )
    }

    override fun mapDomainToData(toData: ArticleDomain): ArticleData {
        return ArticleData(
            author = toData.author,
            content = toData.description,
            description = toData.description,
            publishedAt = toData.publishedAt,
            source = SourceData(toData.source.id, toData.source.name),
            title = toData.title,
            url = toData.url,
            urlToImage = toData.urlToImage
        )
    }
}