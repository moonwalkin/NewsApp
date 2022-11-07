package com.example.newsapp.domain.usecases

import com.example.newsapp.domain.entities.ArticleDomain
import com.example.newsapp.domain.repository.NewsRepository
import javax.inject.Inject

class DeleteArticleUseCase @Inject constructor(private val repository: NewsRepository) {
    suspend operator fun invoke(articleDomain: ArticleDomain) {
        repository.delete(articleDomain)
    }
}