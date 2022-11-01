package com.example.newsapp

import androidx.fragment.app.Fragment
import com.example.newsapp.domain.entities.ArticleDomain

interface Navigator {

    fun openArticle(article: ArticleDomain)


    fun popBackStack()
}

fun Fragment.navigate(): Navigator {
    return requireActivity() as Navigator
}