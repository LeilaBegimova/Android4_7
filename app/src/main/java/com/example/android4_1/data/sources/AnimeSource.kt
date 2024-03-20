package com.example.android4_1.data.sources

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.android4_1.data.remote.apiservices.KitsuApiService
import com.example.android4_1.data.remote.models.Data

class AnimeSource(
    private val mangaApiService: KitsuApiService
) : PagingSource<Int, Data>() {

    override fun getRefreshKey(state: PagingState<Int, Data>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Data> {
        return try {
            val nextPageNumber = params.key ?: 1
            val response = mangaApiService.getAnime(nextPageNumber, params.loadSize)
            val data = response.data
            val prevKey = if (nextPageNumber == 1) null else nextPageNumber - 1
            val nextKey = if (data.isNotEmpty()) nextPageNumber + 1 else null

            LoadResult.Page(data = data, prevKey = prevKey, nextKey = nextKey)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}

