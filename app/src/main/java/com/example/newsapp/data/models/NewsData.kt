package com.example.newsapp.data.models

import com.squareup.moshi.Json


data class NewsData(
    @Json(name = "articles")
    val articles: List<ArticleData>,
    @Json(name = "status")
    val status: String,
    @Json(name = "totalResults")
    val totalResults: Int
)