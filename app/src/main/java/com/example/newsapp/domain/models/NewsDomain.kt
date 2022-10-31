package com.example.newsapp.domain.models


import com.google.gson.annotations.SerializedName

data class NewsDomain(
    @SerializedName("articles")
    val articles: List<ArticleDomain>,
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResults")
    val totalResults: Int
)