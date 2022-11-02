package com.example.newsapp.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.newsapp.App
import com.example.newsapp.ArticleAdapter
import com.example.newsapp.ClickListener
import com.example.newsapp.databinding.FragmentNewsBinding
import com.example.newsapp.domain.entities.ArticleDomain
import com.example.newsapp.navigate
import com.example.newsapp.presentation.viewmodels.NewsViewModel
import com.example.newsapp.presentation.viewmodels.ViewModelFactory
import javax.inject.Inject

class NewsFragment : Fragment() {

    private var _binding: FragmentNewsBinding? = null
    private val binding: FragmentNewsBinding
        get() = checkNotNull(_binding) { "FragmentNewsBinding == null" }

    @Inject
    lateinit var factory: ViewModelFactory

    private lateinit var viewModel: NewsViewModel

    private val component by lazy {
        (requireActivity().application as App).component
    }
    private val newsAdapter by lazy {
        ArticleAdapter(object : ClickListener {
            override fun openPost(articleDomain: ArticleDomain) {
                navigate().openArticlePage(articleDomain)
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
        viewModel = ViewModelProvider(this, factory)[NewsViewModel::class.java]
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