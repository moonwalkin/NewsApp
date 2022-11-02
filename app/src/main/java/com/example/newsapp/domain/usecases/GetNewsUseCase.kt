package com.example.newsapp.domain.usecases

import com.example.newsapp.domain.NewsRepository
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(private val repository: NewsRepository) {

    suspend operator fun invoke() = repository.fetchNews()
}