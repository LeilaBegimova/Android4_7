package com.example.android4_1.data.sources

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.android4_1.data.remote.apiservices.KitsuApiService
import com.example.android4_1.data.remote.models.Data

class MangaSource(
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
            val response = mangaApiService.getManga(nextPageNumber, params.loadSize)
            val data = response.data
            val nextKey = Uri.parse(response.links.next).getQueryParameter("page[offset]")!!.toInt()
            LoadResult.Page(data = data, prevKey = null, nextKey = nextKey)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}