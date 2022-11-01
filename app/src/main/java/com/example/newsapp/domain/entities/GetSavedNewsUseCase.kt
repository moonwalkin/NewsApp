package com.example.newsapp.domain.entities

import com.example.newsapp.domain.NewsRepository

class GetSavedNewsUseCase(private val repository: NewsRepository) {
    operator fun invoke() = repository.getSavedNews()
}