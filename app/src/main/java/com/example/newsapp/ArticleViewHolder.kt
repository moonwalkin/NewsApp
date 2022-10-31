package com.example.newsapp

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ArticleViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    val ivPoster = view.findViewById<ImageView>(R.id.ivPoster)
    val tvSource = view.findViewById<TextView>(R.id.tvSource)
    val tvDate = view.findViewById<TextView>(R.id.tvDate)
    val tvTitle = view.findViewById<TextView>(R.id.tvTitle)
    val tvDescription = view.findViewById<TextView>(R.id.tvDescription)
}