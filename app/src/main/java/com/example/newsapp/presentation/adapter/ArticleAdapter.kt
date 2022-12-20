package com.example.newsapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.newsapp.databinding.NewsItemBinding
import com.example.newsapp.domain.entities.ArticleDomain

class ArticleAdapter(
    private val clickListener: ClickListener
) : ListAdapter<ArticleDomain, ArticleViewHolder>(DiffUtilCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val view = NewsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArticleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickListener)
    }
}

interface ClickListener {
    fun click(article: ArticleDomain)
}


