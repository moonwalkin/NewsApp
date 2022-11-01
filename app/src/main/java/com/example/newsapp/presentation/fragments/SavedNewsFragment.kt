package com.example.newsapp.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.newsapp.ArticleAdapter
import com.example.newsapp.ClickListener
import com.example.newsapp.databinding.FragmentSavedNewsBinding
import com.example.newsapp.domain.entities.ArticleDomain
import com.example.newsapp.navigate
import com.example.newsapp.presentation.viewmodels.SavedNewsViewModel

class SavedNewsFragment : Fragment() {
    private val newsAdapter by lazy {
        ArticleAdapter(object : ClickListener {
            override fun openPost(articleDomain: ArticleDomain) {
                navigate().openArticlePage(articleDomain)
            }

            override fun save(article: ArticleDomain) {

            }
        })
    }
    private var _binding: FragmentSavedNewsBinding? = null
    private val binding: FragmentSavedNewsBinding
        get() = checkNotNull(_binding) { "FragmentSavedNewsBinding == null" }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSavedNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.newsRecycler.adapter = newsAdapter
        val viewModel = ViewModelProvider(this)[SavedNewsViewModel::class.java]
        viewModel._savedNews.observe(viewLifecycleOwner) {
            newsAdapter.submitList(it)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}