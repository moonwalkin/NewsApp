package com.example.newsapp.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ArticleDomain(
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String?,
    val source: SourceDomain?,
    val title: String?,
    val url: String,
    val urlToImage: String?
) : Parcelable