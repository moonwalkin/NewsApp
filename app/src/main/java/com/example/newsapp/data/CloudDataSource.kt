package com.example.newsapp.data

interface CloudDataSource {

    suspend fun fetch(): NewsData
}