package com.example.newsapp.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.App
import com.example.newsapp.R
import com.example.newsapp.domain.entities.ArticleDomain
import com.example.newsapp.presentation.adapter.ArticleAdapter
import com.example.newsapp.presentation.adapter.ClickListener
import com.example.newsapp.presentation.viewmodels.SearchViewModel
import com.example.newsapp.presentation.viewmodels.ViewModelFactory
import kotlinx.coroutines.delay
import javax.inject.Inject

class SearchFragment : Fragment() {
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
    ): View? {
        return inflater.inflate(R.layout.fragment_search_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val vAdapter = ArticleAdapter(object : ClickListener {
            override fun openPost(article: ArticleDomain) {

            }
        })
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = vAdapter
        val viewModel = ViewModelProvider(this, factory)[SearchViewModel::class.java]
        val et = view.findViewById<EditText>(R.id.etSearch)
        val progressBar = view.findViewById<ProgressBar>(R.id.progressBar)
        viewModel.news.observe(viewLifecycleOwner) {
            vAdapter.submitList(it.articles)
        }
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            delay(2000)
            et.addTextChangedListener {
                if (it.toString().isNotEmpty()) {
                    viewModel.fetch(it.toString())
                }
            }
        }


        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            delay(3000)
            progressBar.isVisible = false
        }
    }


}
