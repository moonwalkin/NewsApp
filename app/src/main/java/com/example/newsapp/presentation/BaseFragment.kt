package com.example.newsapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.newsapp.domain.entities.ArticleDomain
import com.example.newsapp.domain.entities.NewsDomain
import com.example.newsapp.navigate
import com.example.newsapp.presentation.adapter.ArticleAdapter
import com.example.newsapp.presentation.adapter.ClickListener
import com.example.newsapp.presentation.viewmodels.ViewModelFactory
import javax.inject.Inject
import com.example.newsapp.domain.Result


abstract class BaseFragment<VB : ViewBinding, VM : ViewModel> : Fragment() {
    private var _binding: VB? = null
    protected val binding
        get() = checkNotNull(_binding)

    abstract val viewModel: VM

    abstract fun getViewBinding(): VB


    @Inject
    lateinit var factory: ViewModelFactory


    protected val component by lazy {
        (requireActivity().application as App).component
    }
    protected val adapter by lazy {
        ArticleAdapter(object : ClickListener {
            override fun click(article: ArticleDomain) {
                navigate().openArticlePage(article)
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = getViewBinding()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    protected fun renderState(
        state: Result<NewsDomain>,
        tvError: TextView,
        progressBar: ProgressBar,
        recyclerView: RecyclerView
    ) {
        when (state) {
            is Result.Loading -> {
                showProgressOrError(true, tvError, progressBar)
            }
            is Result.Success -> {
                progressBar.isVisible = false
                state.data?.articles.let {
                    adapter.submitList(it)
                }
            }
            is Result.Error -> {
                recyclerView.isVisible = false
                showProgressOrError(false, tvError, progressBar)
            }
        }
    }

    private fun showProgressOrError(show: Boolean, tvError: TextView, progressBar: ProgressBar) {
        binding.apply {
            tvError.isVisible = !show
            progressBar.isVisible = show
        }
    }
}