package com.example.newsapp.domain


sealed class Result<T>(
    val data: T? = null,
    val exception: Exception? = null
) {
    class Success<T>(data: T) : Result<T>(data = data)
    class Error<T>(e: Exception) : Result<T>(exception = e)
    class Loading<T> : Result<T>()
}