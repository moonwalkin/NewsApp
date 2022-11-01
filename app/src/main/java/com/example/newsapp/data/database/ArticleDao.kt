package com.example.newsapp.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newsapp.data.models.ArticleData

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(article: ArticleData)

    @Query("SELECT * FROM article_table")
    fun getAllSavedArticles(): LiveData<List<ArticleData>>

    @Delete
    suspend fun delete(article: ArticleData)
}