package com.example.newsapp.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.newsapp.data.CacheDataSource
import com.example.newsapp.data.cloudsource.CloudDataSource
import com.example.newsapp.data.mapper.ArticleMapper
import com.example.newsapp.data.mapper.NewsMapper
import com.example.newsapp.domain.NewsRepository
import com.example.newsapp.domain.entities.ArticleDomain
import com.example.newsapp.domain.entities.NewsDomain


class NewsRepositoryImpl(
    private val cloudDataSource: CloudDataSource,
    private val cacheDataSource: CacheDataSource
) : NewsRepository {

    private val mapper = ArticleMapper()
    override suspend fun fetchNews(): NewsDomain {
        return NewsMapper().mapToDomain(cloudDataSource.fetch())
    }

    override fun getSavedNews(): LiveData<List<ArticleDomain>> = Transformations.map(
        cacheDataSource.getSavedNews()
    ) {
        mapper.mapListDataToDomain(it)
    }

    override suspend fun save(articleDomain: ArticleDomain) {
        cacheDataSource.save(mapper.mapDomainToData(articleDomain))
    }

    override suspend fun delete(articleDomain: ArticleDomain) {
        cacheDataSource.delete(mapper.mapDomainToData(articleDomain))
    }
}