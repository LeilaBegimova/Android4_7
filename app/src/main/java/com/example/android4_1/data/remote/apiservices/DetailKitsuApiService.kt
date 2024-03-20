package com.example.android4_1.data.remote.apiservices

import com.example.android4_1.data.remote.models.DetailResponse
import retrofit2.http.GET
import retrofit2.http.Path

private const val DETAIL_END_POINT_MANGA = "manga/{id}"
private const val DETAIL_END_POINT_ANIME = "anime/{id}"

interface DetailKitsuApiService {
    @GET(DETAIL_END_POINT_MANGA)
    suspend fun getAnimeById(
        @Path("id") id: Int
    ): DetailResponse

    @GET(DETAIL_END_POINT_ANIME)
    suspend fun getMangaById(
        @Path("id") id: Int
    ): DetailResponse
}