package com.example.newsapp.di

import androidx.lifecycle.ViewModel
import com.example.newsapp.presentation.viewmodels.NewsViewModel
import com.example.newsapp.presentation.viewmodels.SavedNewsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @IntoMap
    @Binds
    @ViewModelKey(NewsViewModel::class)
    fun bindNewsViewModel(viewModel: NewsViewModel): ViewModel


    @IntoMap
    @Binds
    @ViewModelKey(SavedNewsViewModel::class)
    fun bindSavedNewsViewModel(viewModel: SavedNewsViewModel): ViewModel
}