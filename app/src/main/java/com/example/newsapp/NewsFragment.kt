package com.example.newsapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.newsapp.databinding.FragmentNewsBinding
import com.example.newsapp.domain.models.ArticleDomain
import com.example.newsapp.presentation.NewsViewModel

class NewsFragment : Fragment() {
    private var _binding: FragmentNewsBinding? = null
    private val binding: FragmentNewsBinding
        get() = checkNotNull(_binding) { "FragmentNewsBinding == null" }

    private val newsAdapter by lazy {
        ArticleAdapter(object : ClickListener {
            override fun openPost(articleDomain: ArticleDomain) {
                navigate().openArticle(articleDomain)
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel = ViewModelProvider(this)[NewsViewModel::class.java]
        binding.newsRecycler.adapter = newsAdapter
        viewModel.news.observe(viewLifecycleOwner) {
            newsAdapter.submitList(it.articles)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}