package com.example.newsapp

import androidx.recyclerview.widget.DiffUtil
import com.example.newsapp.data.ArticleData
import com.example.newsapp.domain.models.ArticleDomain

object DiffUtillCallback : DiffUtil.ItemCallback<ArticleDomain>() {
    override fun areItemsTheSame(oldItem: ArticleDomain, newItem: ArticleDomain): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: ArticleDomain, newItem: ArticleDomain): Boolean {
        return oldItem == newItem
    }
}