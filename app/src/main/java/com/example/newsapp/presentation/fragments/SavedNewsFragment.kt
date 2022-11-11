package com.example.newsapp.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.presentation.App
import com.example.newsapp.ArticleAdapter
import com.example.newsapp.ClickListener
import com.example.newsapp.databinding.FragmentSavedNewsBinding
import com.example.newsapp.domain.entities.ArticleDomain
import com.example.newsapp.navigate
import com.example.newsapp.presentation.BaseFragment
import com.example.newsapp.presentation.viewmodels.SavedNewsViewModel
import com.example.newsapp.presentation.viewmodels.ViewModelFactory
import javax.inject.Inject

class SavedNewsFragment : BaseFragment<FragmentSavedNewsBinding>() {

    private val component by lazy {
        (requireActivity().application as App).component
    }
    @Inject
    lateinit var factory: ViewModelFactory

    private lateinit var viewModel: SavedNewsViewModel

    private val newsAdapter by lazy {
        ArticleAdapter(object : ClickListener {
            override fun openPost(articleDomain: ArticleDomain) {
                navigate().openArticlePage(articleDomain)
            }

            override fun save(article: ArticleDomain) {

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
        setupSwipe()
    }

    private fun observeViewModel() {
        viewModel = ViewModelProvider(this, factory)[SavedNewsViewModel::class.java]
        viewModel.savedNews.observe(viewLifecycleOwner) {
            newsAdapter.submitList(it)
        }
    }

    private fun setupSwipe() {
        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = newsAdapter.currentList[viewHolder.adapterPosition]
                viewModel.delete(item)
            }
        }
        ItemTouchHelper(callback).attachToRecyclerView(binding.newsRecycler)
    }

    override fun getViewBinding() = FragmentSavedNewsBinding.inflate(layoutInflater)

}