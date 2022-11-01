package com.example.newsapp.data.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.newsapp.data.Converters
import com.example.newsapp.data.models.ArticleData

@TypeConverters(Converters::class)
@Database(entities = [ArticleData::class], version =21)
abstract class SavedArticleDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao


    companion object {
        private var INSTANCE: SavedArticleDatabase? = null

        fun getInstance(application: Application): SavedArticleDatabase {
            INSTANCE?.let {
                return it
            }
            synchronized(this) {
                INSTANCE?.let {
                    return it
                }
                val db = Room.databaseBuilder(
                    application,
                    SavedArticleDatabase::class.java,
                    "saved_article"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = db
                return db
            }
        }
    }
}