package com.example.android4_1.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.android4_1.data.remote.apiservices.DetailKitsuApiService
import com.example.android4_1.data.remote.apiservices.KitsuApiService
import com.example.android4_1.data.sources.AnimeSource
import com.example.android4_1.data.sources.MangaSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class KitsuRepository @Inject constructor(
    private val kitsuApiService: KitsuApiService,
    private val detailKitsuApi: DetailKitsuApiService,
) {

    fun fetchManga() = Pager(
        config = PagingConfig(
            pageSize = 20,
            initialLoadSize = 20,
            enablePlaceholders = true
        ),
        pagingSourceFactory = { MangaSource(kitsuApiService) }
    ).flow

    fun fetchAnime() = Pager(
        config = PagingConfig(
            pageSize = 20,
            initialLoadSize = 20,
            enablePlaceholders = true
        ),
        pagingSourceFactory = { AnimeSource(kitsuApiService) }
    ).flow

    fun getMangaById(id: Int) = flow {
        emit(detailKitsuApi.getMangaById(id).data)
    }.flowOn(Dispatchers.IO)

    fun getAnimeById(id: Int) = flow {
        emit(detailKitsuApi.getAnimeById(id).data)
    }.flowOn(Dispatchers.IO)
}