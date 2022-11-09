package com.example.newsapp.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.newsapp.data.cache.CacheDataSource
import com.example.newsapp.data.cloud.CloudDataSource
import com.example.newsapp.data.mapper.ArticleMapper
import com.example.newsapp.data.mapper.NewsMapper
import com.example.newsapp.domain.Results
import com.example.newsapp.domain.entities.ArticleDomain
import com.example.newsapp.domain.entities.NewsDomain
import com.example.newsapp.domain.repository.NewsRepository
import javax.inject.Inject


class NewsRepositoryImpl @Inject constructor(
    private val cloudDataSource: CloudDataSource,
    private val cacheDataSource: CacheDataSource,
    private val articleMapper: ArticleMapper,
    private val newsMapper: NewsMapper
) : NewsRepository {

    override suspend fun fetchTopNews(): Results<NewsDomain> {
        return try {
            Results.Success(newsMapper.mapToDomain(cloudDataSource.fetch()))
        } catch (e: Exception) {
            Results.Error(e)
        }
    }

    override suspend fun fetchNewsByQuery(query: String): Results<NewsDomain> {
        return try {
            Results.Success(newsMapper.mapToDomain(cloudDataSource.fetchNewsByQuery(query)))
        } catch (e: Exception) {
            Results.Error(e)
        }
    }

    override fun getSavedNews(): LiveData<List<ArticleDomain>> = Transformations.map(
        cacheDataSource.getSavedNews()
    ) {
        articleMapper.mapListDataToDomain(it)
    }

    override suspend fun save(articleDomain: ArticleDomain) {
        cacheDataSource.save(articleMapper.mapDomainToData(articleDomain))
    }

    override suspend fun delete(articleDomain: ArticleDomain) {
        cacheDataSource.delete(articleMapper.mapDomainToData(articleDomain))
    }
}