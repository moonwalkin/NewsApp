package com.example.newsapp.domain.entities

import com.example.newsapp.domain.NewsRepository

class DeleteUseCase(private val repository: NewsRepository) {
    suspend operator fun invoke(articleDomain: ArticleDomain) {
        repository.delete(articleDomain)
    }
}