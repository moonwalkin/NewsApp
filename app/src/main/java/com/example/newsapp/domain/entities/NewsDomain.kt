package com.example.newsapp.domain.entities


data class NewsDomain(
    val articles: List<ArticleDomain>,
    val status: String,
    val totalResults: Int
)