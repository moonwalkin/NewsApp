package com.example.newsapp.domain.usecases

import com.example.newsapp.domain.repository.NewsRepository
import javax.inject.Inject

class GetSavedNewsUseCase @Inject constructor(private val repository: NewsRepository) {
    operator fun invoke() = repository.getSavedNews()
}