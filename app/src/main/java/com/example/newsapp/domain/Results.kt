package com.example.newsapp.domain


sealed class Results<T>(
    val data: T? = null,
    val exception: Exception? = null
) {
    class Success<T>(data: T) : Results<T>(data = data)
    class Error<T>(e: Exception) : Results<T>(exception = e)
    class Loading<T> : Results<T>()
}