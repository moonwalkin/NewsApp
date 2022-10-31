package com.example.newsapp.data.network

import com.example.newsapp.data.NewsData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {
    @GET("v2/top-headlines")
    fun fetch(
        @Query("apiKey") apiKey: String,
        @Query("country") country: String
    ): Call<NewsData>
}