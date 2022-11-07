package com.example.newsapp.domain.usecases

import com.example.newsapp.domain.repository.NewsRepository
import javax.inject.Inject

class GetNewsByQueryUseCase @Inject constructor(private val repository: NewsRepository) {
    suspend operator fun invoke(query: String) = repository.fetchNewsByQuery(query)
}