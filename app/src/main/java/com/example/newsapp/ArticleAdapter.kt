package com.example.newsapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.example.newsapp.domain.models.ArticleDomain

class ArticleAdapter(
    private val clickListener: ClickListener
) : ListAdapter<ArticleDomain, ArticleViewHolder>(DiffUtillCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)
        return ArticleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val item = getItem(position)
        holder.apply {
            item.apply {
                tvSource.text = source?.name
                tvDate.text = publishedAt
                tvTitle.text = title
                tvDescription.text = description
                Glide.with(view).load(urlToImage).into(ivPoster)

            }
            itemView.setOnClickListener {
                clickListener.openPost(item)
            }
        }
    }
}

interface ClickListener {
    fun openPost(articleDomain: ArticleDomain)
}