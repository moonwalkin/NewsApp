package com.example.newsapp.presentation.fragments


import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.example.newsapp.*
import com.example.newsapp.databinding.FragmentNewsBinding
import com.example.newsapp.domain.Results
import com.example.newsapp.domain.entities.ArticleDomain
import com.example.newsapp.presentation.App
import com.example.newsapp.presentation.BaseFragment
import com.example.newsapp.presentation.viewmodels.NewsViewModel
import com.example.newsapp.presentation.viewmodels.ViewModelFactory
import javax.inject.Inject

class NewsFragment : BaseFragment<FragmentNewsBinding>() {

    @Inject
    lateinit var factory: ViewModelFactory

    private lateinit var viewModel: NewsViewModel

    private val component by lazy {
        (requireActivity().application as App).component
    }
    private val newsAdapter by lazy {
        ArticleAdapter(object : ClickListener {
            override fun openPost(article: ArticleDomain) {
                navigate().openArticlePage(article)
            }

            override fun save(article: ArticleDomain) {
                viewModel.save(article)
            }
        })
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.newsRecycler.adapter = newsAdapter
        observeViewModel()
    }

    override fun getViewBinding() = FragmentNewsBinding.inflate(layoutInflater)

    private fun observeViewModel() {

        viewModel = ViewModelProvider(this, factory)[NewsViewModel::class.java]

        viewModel.news.observe(viewLifecycleOwner) { result ->
            when (result) {
                is Results.Loading -> {
                    binding.apply {
                        tvError.isVisible = false
                        progressBar.isVisible = true
                    }
                }
                is Results.Success -> {
                    binding.apply {
                        progressBar.isVisible = false
                        tvError.isVisible = false
                    }
                    result.data?.articles.let {
                        newsAdapter.submitList(it)
                    }
                }
                is Results.Error -> {
                    binding.apply {
                        newsRecycler.isVisible = false
                        progressBar.isVisible = false
                        tvError.isVisible = true
                    }
                }
            }
        }
    }


}