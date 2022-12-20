package com.example.newsapp.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.domain.entities.ArticleDomain
import com.example.newsapp.domain.usecases.SaveArticleUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class ArticleViewModel @Inject constructor(
    private val saveNewsUseCase: SaveArticleUseCase
    ) : ViewModel() {

    fun save(article: ArticleDomain) {
        viewModelScope.launch {
            saveNewsUseCase(article)
        }
    }
}