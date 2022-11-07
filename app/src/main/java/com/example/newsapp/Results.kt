package com.example.newsapp


sealed class Results<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : Results<T>(data = data)
    class Error<T>(message: String) : Results<T>(message = message)
    class Loading<T> : Results<T>()
}