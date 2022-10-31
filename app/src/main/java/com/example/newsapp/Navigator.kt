package com.example.newsapp

import androidx.fragment.app.Fragment

interface Navigator {

    fun openArticle(article: String)
}

fun Fragment.navigate(): Navigator {
    return requireActivity() as Navigator
}