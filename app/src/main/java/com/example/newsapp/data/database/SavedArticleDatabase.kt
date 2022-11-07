package com.example.newsapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.newsapp.data.models.ArticleData

@TypeConverters(Converters::class)
@Database(entities = [ArticleData::class], version = 1)
abstract class SavedArticleDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao
}