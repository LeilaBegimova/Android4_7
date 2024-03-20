package com.example.android4_1.data.remote.models

import com.google.gson.annotations.SerializedName

data class Attributes(
    @SerializedName("abbreviatedTitles")
    val abbreviatedTitles: List<String>,
    @SerializedName("ageRating")
    val ageRating: String,
    @SerializedName("coverImageTopOffset")
    val coverImageTopOffset: Int,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("endDate")
    val endDate: String,
    @SerializedName("mangaType")
    val mangaType: String,
    @SerializedName("posterImage")
    val posterImage: PosterImage,
    @SerializedName("startDate")
    val startDate: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("titles")
    val titles: Titles,
    @SerializedName("coverImage")
    val coverImage: CoverImage? = null,
)