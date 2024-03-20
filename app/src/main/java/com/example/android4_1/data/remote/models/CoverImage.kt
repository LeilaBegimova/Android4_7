package com.example.android4_1.data.remote.models

import com.google.gson.annotations.SerializedName

data class CoverImage(
    @SerializedName("large")
    val large: String? = null,
)