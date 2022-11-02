package com.example.newsapp.data.network

import androidx.paging.PagingData
import com.example.newsapp.data.models.NewsData
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("v2/top-headlines")
    suspend fun fetch(
        @Query("apiKey") apiKey: String = "0c1198243f494b6ca3a0e6f0444e7e1c",
        @Query("country") country: String = "ua",
        @Query("page") page: Int = 1,
        @Query("pageSize") pageSize: Int = 20
    ): NewsData

//    @GET("v2/top-headlines")
//    suspend fun fetch(
//        @Query("apiKey") apiKey: String = "0c1198243f494b6ca3a0e6f0444e7e1c",
//        @Query("country") country: String = "ua",
//        @Query("page") page: Int = 1,
//        @Query("pageSize") pageSize: Int = 20
//    ): Flow<PagingData<NewsData>>
}