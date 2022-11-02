package com.example.newsapp.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.example.newsapp.databinding.FragmentArticleBinding
import com.example.newsapp.domain.entities.ArticleDomain
import com.example.newsapp.navigate

class ArticleFragment : Fragment() {

    private var _binding: FragmentArticleBinding? = null
    private val binding: FragmentArticleBinding
        get() = checkNotNull(_binding) { "FragmentArticleBinding == null" }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentArticleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val article = requireArguments().getParcelable<ArticleDomain>(ARTICLE)
        binding.webView.apply {
            webViewClient = WebViewClient()
            loadUrl(article?.url!!)
            handleOnBackPressed(this)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


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