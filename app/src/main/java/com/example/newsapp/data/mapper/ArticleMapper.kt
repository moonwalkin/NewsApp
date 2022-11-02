package com.example.newsapp.data.mapper

import com.example.newsapp.data.models.ArticleData
import com.example.newsapp.data.models.SourceData
import com.example.newsapp.domain.entities.ArticleDomain
import com.example.newsapp.domain.entities.SourceDomain
import javax.inject.Inject

class ArticleMapper @Inject constructor(){
    fun mapDataToDomain(item: ArticleData): ArticleDomain {
        return ArticleDomain(
            author = item.author,
            content = item.description,
            description = item.description,
            publishedAt = item.publishedAt,
            source = SourceDomain(item.source?.id ?: "", item.source?.name ?: ""),
            title = item.title,
            url = item.url,
            urlToImage = item.urlToImage
        )
    }
    fun mapDomainToData(item: ArticleDomain): ArticleData {
        return ArticleData(
            author = item.author!!,
            content = item.description!!,
            description = item.description!!,
            publishedAt = item.publishedAt!!,
            source = SourceData(item.source?.id ?: "", item.source?.name ?: ""),
            title = item.title!!,
            url = item.url,
            urlToImage = item.urlToImage!!
        )
    }

    fun mapListDataToDomain(list: List<ArticleData>) = list.map {
        mapDataToDomain(it)
    }


}