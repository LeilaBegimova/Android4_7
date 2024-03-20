package com.example.android4_1.data.remote.apiservices

import com.example.android4_1.data.remote.models.MangaResponse
import retrofit2.http.GET
import retrofit2.http.Query

private const val MANGA_END_POINT = "manga"
private const val ANIME_END_POINT = "anime"

interface KitsuApiService {

    @GET(MANGA_END_POINT)
    suspend fun getManga(
        @Query("page[offset]") offset: Int,
        @Query("page[limit]") limit: Int,
    ): MangaResponse

    @GET(ANIME_END_POINT)
    suspend fun getAnime(
        @Query("page[offset]") offset: Int,
        @Query("page[limit]") limit: Int,
    ): MangaResponse
}