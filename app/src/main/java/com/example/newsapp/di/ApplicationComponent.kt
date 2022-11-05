package com.example.newsapp.di

import android.app.Application
import com.example.newsapp.presentation.fragments.NewsFragment
import com.example.newsapp.presentation.fragments.SavedNewsFragment
import com.example.newsapp.presentation.fragments.SearchFragment
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(
    modules = [
        DataModule::class,
        ViewModelModule::class
    ]
)
interface ApplicationComponent {

    fun inject(fragment: SavedNewsFragment)
    fun inject(fragment: SearchFragment)
    fun inject(fragment: NewsFragment)

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }
}