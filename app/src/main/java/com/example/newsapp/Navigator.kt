package com.example.newsapp

import android.webkit.WebView
import androidx.fragment.app.Fragment
import com.example.newsapp.domain.models.ArticleDomain

interface Navigator {

    fun openArticle(article: ArticleDomain)


    fun popBackStack()
}

fun Fragment.navigate(): Navigator {
    return requireActivity() as Navigator
}