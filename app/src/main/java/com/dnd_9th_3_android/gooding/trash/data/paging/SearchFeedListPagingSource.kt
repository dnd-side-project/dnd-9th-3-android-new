package com.dnd_9th_3_android.gooding.trash.data.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.dnd_9th_3_android.gooding.trash.data.model.search.SearchFeedResponse
import com.dnd_9th_3_android.gooding.trash.data.remote.search.SearchFeedListRemoteDataSource

class SearchFeedListPagingSource(
    private val searchFeedListRemoteDataSource: SearchFeedListRemoteDataSource,
    private val query: String
) : PagingSource<Int, SearchFeedResponse>() {
    override fun getRefreshKey(state: PagingState<Int, SearchFeedResponse>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SearchFeedResponse> {
        val page = params.key ?: 1
        Log.d("feedRequest page", "$page")

        val searchFeedResponse =
            kotlin.runCatching {
                searchFeedListRemoteDataSource.searchFeedList(
                    page = page,
                    query = query
                )
            }

        val list = searchFeedResponse.getOrNull()?.result.orEmpty()

        return LoadResult.Page(
            data = list,
            prevKey = null,
            nextKey = if (list.isNotEmpty()) page + 1 else null
        )
    }
}