package com.example.newsapp.presentation.viewmodels

import androidx.lifecycle.*
import com.example.newsapp.Results
import com.example.newsapp.domain.entities.ArticleDomain
import com.example.newsapp.domain.entities.NewsDomain
import com.example.newsapp.domain.usecases.GetNewsUseCase
import com.example.newsapp.domain.usecases.SaveArticleUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

class NewsViewModel @Inject constructor(
    private val saveNewsUseCase: SaveArticleUseCase,
    private val getNewsUseCase: GetNewsUseCase
) : ViewModel() {

    private val _news = MutableLiveData<Results<NewsDomain>>()
    val news: LiveData<Results<NewsDomain>>
        get() = _news


    init {
        fetch()
    }

    private fun fetch() = viewModelScope.launch {
        _news.postValue(Results.Loading())
        _news.postValue(Results.Success(getNewsUseCase()))
    }

    fun save(article: ArticleDomain) {
        viewModelScope.launch {
            saveNewsUseCase(article)
        }
    }
}
