package com.example.newsapp.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class SourceDomain(
    val id: String?,
    val name: String
) : Parcelable