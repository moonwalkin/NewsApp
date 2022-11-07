package com.example.newsapp.di

import android.app.Application
import androidx.room.Room
import com.example.newsapp.data.cache.CacheDataSource
import com.example.newsapp.data.cache.CacheDataSourceImpl
import com.example.newsapp.data.cloud.CloudDataSource
import com.example.newsapp.data.cloud.CloudDataSourceImpl
import com.example.newsapp.data.database.ArticleDao
import com.example.newsapp.data.database.SavedArticleDatabase
import com.example.newsapp.data.network.NewsService
import com.example.newsapp.data.repository.NewsRepositoryImpl
import com.example.newsapp.domain.repository.NewsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

@Module
interface DataModule {

    @Binds
    fun bindCacheDataSource(impl: CacheDataSourceImpl): CacheDataSource

    @Binds
    fun bindCloudDataSource(impl: CloudDataSourceImpl): CloudDataSource

    @Binds
    @ApplicationScope
    fun bindNewsRepository(impl: NewsRepositoryImpl): NewsRepository


    companion object {

        @Provides
        fun provideDao(application: Application): ArticleDao {
            return Room.databaseBuilder(
                application,
                SavedArticleDatabase::class.java,
                "saved_article"
            )
                .build()
                .articleDao()
        }

        @Provides
        fun provideService(): NewsService {
            return Retrofit.Builder()
                .baseUrl("https://newsapi.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create()
        }
    }
}