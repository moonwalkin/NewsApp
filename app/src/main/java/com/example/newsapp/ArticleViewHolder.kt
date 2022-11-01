package com.example.newsapp

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.databinding.NewsItemBinding
import com.example.newsapp.domain.models.ArticleDomain

class ArticleViewHolder(val binding: NewsItemBinding) : RecyclerView.ViewHolder(binding.root) {
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
            }
        }
    }
}