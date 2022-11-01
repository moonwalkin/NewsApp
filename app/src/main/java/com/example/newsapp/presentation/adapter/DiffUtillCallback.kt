package com.example.newsapp.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.newsapp.domain.entities.ArticleDomain

object DiffUtillCallback : DiffUtil.ItemCallback<ArticleDomain>() {
    override fun areItemsTheSame(oldItem: ArticleDomain, newItem: ArticleDomain): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: ArticleDomain, newItem: ArticleDomain): Boolean {
        return oldItem == newItem
    }
}