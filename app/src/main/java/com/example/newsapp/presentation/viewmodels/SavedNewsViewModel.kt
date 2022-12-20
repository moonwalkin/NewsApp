package com.example.newsapp.presentation.viewmodels

import androidx.lifecycle.viewModelScope
import com.example.newsapp.domain.entities.ArticleDomain
import com.example.newsapp.domain.usecases.DeleteArticleUseCase
import com.example.newsapp.domain.usecases.GetSavedNewsUseCase
import com.example.newsapp.presentation.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class SavedNewsViewModel @Inject constructor(
    private val getSavedNewsUseCase: GetSavedNewsUseCase,
    private val deleteUseCase: DeleteArticleUseCase
) : BaseViewModel() {
    val savedNews = getSavedNewsUseCase()

    fun delete(article: ArticleDomain) {
        viewModelScope.launch {
            deleteUseCase(article)
        }
    }
}