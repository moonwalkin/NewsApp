package com.example.newsapp.presentation.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.ClickListener
import com.example.newsapp.databinding.NewsItemBinding
import com.example.newsapp.domain.entities.ArticleDomain

class ArticleViewHolder(private val binding: NewsItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(article: ArticleDomain, clickListener: ClickListener) {
        binding.apply {
            article.apply {
                tvSource.text = source?.name
                tvDate.text = publishedAt
                tvTitle.text = title
                tvDescription.text = description
                Glide.with(root).load(urlToImage).into(ivPoster)
                root.setOnClickListener {
                    clickListener.openPost(article)
                }
                root.setOnLongClickListener {
                    clickListener.save(article)
                    true
                }
            }
        }
    }
}