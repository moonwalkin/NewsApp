package com.example.newsapp.data.cloudsource

import com.example.newsapp.data.models.NewsData

interface CloudDataSource {

    suspend fun fetch(): NewsData
}