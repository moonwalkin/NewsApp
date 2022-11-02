package com.example.newsapp.presentation.viewmodels

import androidx.lifecycle.*
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

    private val _news = MutableLiveData<NewsDomain>()
    val news: LiveData<NewsDomain>
        get() = _news


    init {
        fetch()
    }

    private fun fetch() = viewModelScope.launch {
        _news.postValue(getNewsUseCase())
    }

    fun save(article: ArticleDomain) {
        viewModelScope.launch {
            saveNewsUseCase(article)
        }
    }
}

class ViewModelFactory @Inject constructor(
    private val viewModelProviders: @JvmSuppressWildcards Map<Class<out ViewModel>, Provider<ViewModel>>
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return viewModelProviders[modelClass]?.get() as T
    }
}