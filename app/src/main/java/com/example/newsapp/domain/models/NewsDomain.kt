package com.example.newsapp.domain.models


data class NewsDomain(
    val articles: List<ArticleDomain>,
    val status: String,
    val totalResults: Int
)