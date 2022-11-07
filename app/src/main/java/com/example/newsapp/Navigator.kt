package com.example.newsapp

import androidx.fragment.app.Fragment
import com.example.newsapp.domain.entities.ArticleDomain

interface Navigator {

    fun openArticlePage(article: ArticleDomain)

    fun popBackStack()

}

fun Fragment.navigate(): Navigator {
    return requireActivity() as Navigator
}