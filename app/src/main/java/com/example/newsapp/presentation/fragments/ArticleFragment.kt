package com.example.newsapp.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import com.example.newsapp.databinding.FragmentArticleBinding
import com.example.newsapp.domain.entities.ArticleDomain
import com.example.newsapp.navigate
import com.example.newsapp.presentation.BaseFragment
import com.example.newsapp.presentation.viewmodels.ArticleViewModel

class ArticleFragment : BaseFragment<FragmentArticleBinding, ArticleViewModel>() {
    override val viewModel: ArticleViewModel by viewModels { factory }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val article = requireArguments().getParcelable<ArticleDomain>(ARTICLE)
        binding.webView.apply {
            webViewClient = WebViewClient()
            loadUrl(article?.url!!)
            handleOnBackPressed(this)
        }
        binding.fabSaveArticle.setOnClickListener {
            viewModel.save(article!!)
        }
    }

    override fun getViewBinding() = FragmentArticleBinding.inflate(layoutInflater)

    private fun handleOnBackPressed(webView: WebView) {
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (webView.canGoBack()) {
                    webView.goBack()
                } else {
                    navigate().popBackStack()
                }
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
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