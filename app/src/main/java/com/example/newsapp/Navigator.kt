package com.example.newsapp

import androidx.fragment.app.Fragment
import com.example.newsapp.domain.models.ArticleDomain

interface Navigator {

    fun openArticle(article: ArticleDomain)
}

fun Fragment.navigate(): Navigator {
    return requireActivity() as Navigator
}