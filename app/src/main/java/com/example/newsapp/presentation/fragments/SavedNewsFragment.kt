package com.example.newsapp.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.databinding.FragmentSavedNewsBinding
import com.example.newsapp.presentation.BaseFragment
import com.example.newsapp.presentation.viewmodels.SavedNewsViewModel

class SavedNewsFragment : BaseFragment<FragmentSavedNewsBinding, SavedNewsViewModel>() {

    override val viewModel: SavedNewsViewModel by viewModels { factory }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.newsRecycler.adapter = adapter
        observeViewModel()
        setupSwipe()
    }

    private fun observeViewModel() {
        viewModel.savedNews.observe(viewLifecycleOwner) {
            adapter.submitList(it)
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
                val item = adapter.currentList[viewHolder.adapterPosition]
                viewModel.delete(item)
            }
        }
        ItemTouchHelper(callback).attachToRecyclerView(binding.newsRecycler)
    }

    override fun getViewBinding() = FragmentSavedNewsBinding.inflate(layoutInflater)

}