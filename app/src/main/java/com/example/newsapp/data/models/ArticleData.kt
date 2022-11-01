package com.example.newsapp.data.models


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
@Entity(tableName = "article_table")
data class ArticleData(
    @SerializedName("author")
    val author: String?,
    @SerializedName("content")
    val content: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("publishedAt")
    val publishedAt: String?,
    @SerializedName("source")
    val source: SourceData?,
    @PrimaryKey
    @SerializedName("title")
    val title: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("urlToImage")
    val urlToImage: String?
)