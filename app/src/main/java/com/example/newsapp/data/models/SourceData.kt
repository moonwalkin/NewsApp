package com.example.newsapp.data.models


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class SourceData(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String
) : Parcelable