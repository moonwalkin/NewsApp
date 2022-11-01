package com.example.newsapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.presentation.NewsViewModel

class NewsFragment : Fragment() {

    private val newsAdapter by lazy {
        ArticleAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById<RecyclerView>(R.id.newsRecycler)
        recyclerView.adapter = newsAdapter
        val viewModel = ViewModelProvider(this)[NewsViewModel::class.java]
        viewModel.news.observe(viewLifecycleOwner) {
            newsAdapter.submitList(it.articles)
        }

    }


}