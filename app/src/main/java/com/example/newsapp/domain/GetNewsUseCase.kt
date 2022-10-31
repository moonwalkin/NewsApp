package com.example.newsapp.domain

class GetNewsUseCase(private val repository: NewsRepository) {

    suspend operator fun invoke() = repository.fetchNews()
}