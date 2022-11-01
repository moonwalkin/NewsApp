package com.example.newsapp.data.network

import com.example.newsapp.data.NewsData
import com.example.newsapp.domain.models.NewsDomain
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {
//    @GET("v2/top-headlines")
//    suspend fun fetch(
//        @Query("apiKey") apiKey: String = "0c1198243f494b6ca3a0e6f0444e7e1c",
//        @Query("country") country: String = "ua"
//    ): NewsData
@GET("v2/top-headlines")
suspend fun fetch(
    @Query("apiKey") apiKey: String = "0c1198243f494b6ca3a0e6f0444e7e1c",
    @Query("country") country: String = "ua"
): NewsData
}