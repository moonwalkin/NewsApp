package com.example.newsapp.domain.entities

import com.example.newsapp.domain.NewsRepository

class SaveArticleUseCase(private val repository: NewsRepository) {
    suspend operator fun invoke(articleDomain: ArticleDomain) {
        repository.save(articleDomain)
    }
}