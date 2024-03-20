package com.example.android4_1.data.remote.models

import com.google.gson.annotations.SerializedName

data class MangaResponse(
    @SerializedName("data")
    val data: List<Data>,
    @SerializedName("links")
    val links: Links,
    @SerializedName("meta")
    val meta:Meta,
)