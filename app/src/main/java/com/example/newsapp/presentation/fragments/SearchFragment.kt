package com.example.newsapp.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.*
import com.example.newsapp.databinding.FragmentSearchNewsBinding
import com.example.newsapp.domain.Results
import com.example.newsapp.domain.entities.ArticleDomain

import com.example.newsapp.presentation.viewmodels.SearchViewModel
import com.example.newsapp.presentation.viewmodels.ViewModelFactory
import kotlinx.coroutines.delay
import javax.inject.Inject

class SearchFragment : Fragment() {
    private lateinit var adapter: ArticleAdapter
    private var _binding: FragmentSearchNewsBinding? = null
    private val binding: FragmentSearchNewsBinding
        get() = checkNotNull(_binding) { "FragmentSearchNewsBinding == null" }

    @Inject
    lateinit var factory: ViewModelFactory

    private val component by lazy {
        (requireActivity().application as App).component
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeViewModel()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

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
        val viewModel = ViewModelProvider(this, factory)[SearchViewModel::class.java]
        viewModel.news.observe(viewLifecycleOwner) { result ->
            when (result) {
                is Results.Loading -> {
                    binding.apply {
                        tvError.isVisible = false
                        progressBar.isVisible = true
                    }
                }
                is Results.Success -> {
                    binding.progressBar.isVisible = false
                    result.data?.articles.let {
                        adapter.submitList(it)
                    }
                }
                is Results.Error -> {
                    binding.apply {
                        recyclerView.isVisible = false
                        progressBar.isVisible = false
                        tvError.isVisible = true
                    }
                }
            }
        }
        binding.etSearch.addTextChangedListener {
            viewLifecycleOwner.lifecycleScope.launchWhenResumed {
                delay(1000)
                if (it.toString().isNotEmpty()) {
                    viewModel.fetch(it.toString())
                }
            }
        }
    }
}
