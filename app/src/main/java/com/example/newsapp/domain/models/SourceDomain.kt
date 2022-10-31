package com.example.newsapp.domain.models


import com.google.gson.annotations.SerializedName

data class SourceDomain(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String
)