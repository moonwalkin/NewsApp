package com.example.newsapp.data.network

import com.example.newsapp.BuildConfig
import com.example.newsapp.data.models.NewsData
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("v2/top-headlines")
    suspend fun fetchTopNews(
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY,
        @Query("country") country: String = "ua",
    ): NewsData

    @GET("v2/everything")
    suspend fun fetchByQuery(
        @Query("q") query: String,
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY,
    ): NewsData
}