package com.example.newsapp.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.databinding.FragmentSearchNewsBinding
import com.example.newsapp.presentation.BaseFragment
import com.example.newsapp.presentation.viewmodels.SearchViewModel
import kotlinx.coroutines.delay

class SearchFragment : BaseFragment<FragmentSearchNewsBinding, SearchViewModel>() {

    override val viewModel: SearchViewModel by viewModels { factory }

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
        binding.apply {
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = adapter
        }
    }


    private fun observeViewModel() {
        viewModel.state.observe(viewLifecycleOwner) { result ->
            binding.apply {
                renderState(result, tvError, progressBar, recyclerView)
            }
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
}
