package com.example.newsapp.domain.models


data class ArticleDomain(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: SourceDomain,
    val title: String,
    val url: String,
    val urlToImage: String
)