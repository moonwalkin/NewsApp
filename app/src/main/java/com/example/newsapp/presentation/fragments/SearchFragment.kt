package com.example.newsapp.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.ArticleAdapter
import com.example.newsapp.ClickListener
import com.example.newsapp.databinding.FragmentSearchNewsBinding
import com.example.newsapp.domain.Results
import com.example.newsapp.domain.entities.ArticleDomain
import com.example.newsapp.domain.entities.NewsDomain
import com.example.newsapp.navigate
import com.example.newsapp.presentation.App
import com.example.newsapp.presentation.BaseFragment
import com.example.newsapp.presentation.viewmodels.SearchViewModel
import com.example.newsapp.presentation.viewmodels.ViewModelFactory
import kotlinx.coroutines.delay
import javax.inject.Inject

class SearchFragment : BaseFragment<FragmentSearchNewsBinding>() {

    private lateinit var adapter: ArticleAdapter

    @Inject
    lateinit var factory: ViewModelFactory

    private lateinit var viewModel: SearchViewModel

    private val component by lazy {
        (requireActivity().application as App).component
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeViewModel()
    }


    override fun getViewBinding() = FragmentSearchNewsBinding.inflate(layoutInflater)

    private fun setupRecyclerView() {
        adapter = ArticleAdapter(object : ClickListener {
            override fun openPost(article: ArticleDomain) {
                navigate().openArticlePage(article)
            }
        })
        binding.apply {
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = adapter
        }
    }


    private fun observeViewModel() {
        viewModel = ViewModelProvider(this, factory)[SearchViewModel::class.java]
        viewModel.news.observe(viewLifecycleOwner) { result ->
            render(result)
        }
        listenTextChange()
    }
    private fun listenTextChange() {
        binding.etSearch.addTextChangedListener {
            viewLifecycleOwner.lifecycleScope.launchWhenResumed {
                delay(1000)
                if (it.toString().isNotEmpty()) {
                    viewModel.fetch(it.toString())
                }
            }
        }
    }

    private fun showProgressBar(show: Boolean) {
        binding.apply {
            tvError.isVisible = !show
            progressBar.isVisible = show
        }
    }

    private fun render(state: Results<NewsDomain>) {
        when (state) {
            is Results.Loading -> {
                binding.apply {
                    showProgressBar(true)
                }
            }
            is Results.Success -> {
                binding.progressBar.isVisible = false
                state.data?.articles.let {
                    adapter.submitList(it)
                }
            }
            is Results.Error -> {
                binding.apply {
                    recyclerView.isVisible = false
                    showProgressBar(false)
                }
            }
        }
    }
}
