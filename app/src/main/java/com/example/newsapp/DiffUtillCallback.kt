package com.example.newsapp

import androidx.recyclerview.widget.DiffUtil
import com.example.newsapp.data.Article

object DiffUtillCallback : DiffUtil.ItemCallback<Article>() {
    override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem == newItem
    }
}