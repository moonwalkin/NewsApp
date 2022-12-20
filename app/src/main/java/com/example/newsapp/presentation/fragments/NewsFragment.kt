package com.example.newsapp.presentation.fragments
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.newsapp.databinding.FragmentNewsBinding
import com.example.newsapp.presentation.BaseFragment
import com.example.newsapp.presentation.viewmodels.NewsViewModel


class NewsFragment : BaseFragment<FragmentNewsBinding, NewsViewModel>() {

    override val viewModel: NewsViewModel by viewModels { factory }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.newsRecycler.adapter = adapter
        observeViewModel()
    }

    override fun getViewBinding() = FragmentNewsBinding.inflate(layoutInflater)

    private fun observeViewModel() {
        viewModel.state.observe(viewLifecycleOwner) { result ->
            binding.apply {
                renderState(result, tvError, progressBar, newsRecycler)
            }
        }
    }
}
