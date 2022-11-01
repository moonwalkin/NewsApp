package com.example.newsapp.data.models


import com.google.gson.annotations.SerializedName

data class NewsData(
    @SerializedName("articles")
    val articles: List<ArticleData>,
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResults")
    val totalResults: Int
)