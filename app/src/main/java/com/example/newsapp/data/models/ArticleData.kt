package com.example.newsapp.data.models


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "article_table")
data class ArticleData(
    @Json(name = "content")
    val content: String?,
    @Json(name = "author")
    val author: String?,
    @Json(name = "description")
    val description: String?,
    @Json(name = "publishedAt")
    val publishedAt: String?,
    @Json(name = "source")
    val source: SourceData?,
    @PrimaryKey
    @Json(name = "title")
    val title: String,
    @Json(name = "url")
    val url: String,
    @Json(name = "urlToImage")
    val urlToImage: String?
)