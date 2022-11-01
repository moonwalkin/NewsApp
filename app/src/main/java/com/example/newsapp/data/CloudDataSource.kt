package com.example.newsapp.data

import com.example.newsapp.domain.models.NewsDomain

interface CloudDataSource {

    suspend fun fetch(): NewsDomain
}