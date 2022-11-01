package com.example.newsapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.newsapp.domain.models.ArticleDomain

class ArticleFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_article, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val webView = view.findViewById<WebView>(R.id.webView)
        webView.webViewClient = WebViewClient()
        val a = requireArguments().getParcelable<ArticleDomain>(ARTICLE)
        webView.loadUrl(a?.url!!)
    }



    companion object {
        private const val ARTICLE = "article"

        fun newInstance(article: ArticleDomain): ArticleFragment {
            return ArticleFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARTICLE, article)
                }
            }
        }
    }

}