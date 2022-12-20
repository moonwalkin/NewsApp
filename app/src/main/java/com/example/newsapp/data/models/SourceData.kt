package com.example.newsapp.data.models


import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize


@Parcelize
data class SourceData(
    @Json(name = "id")
    val id: String,
    @Json(name = "name")
    val name: String
) : Parcelable