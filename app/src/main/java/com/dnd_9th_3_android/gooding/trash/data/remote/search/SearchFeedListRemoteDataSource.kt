package com.dnd_9th_3_android.gooding.trash.data.remote.search

import com.dnd_9th_3_android.gooding.data.model.search.PopularKeywordListResponse
import com.dnd_9th_3_android.gooding.data.model.search.RecentKeywordListResponse
import com.dnd_9th_3_android.gooding.data.model.search.SearchFeedListResponse

interface SearchFeedListRemoteDataSource {
    suspend fun searchFeedList(page: Int, query: String): SearchFeedListResponse

    suspend fun getRecentKeywordList(): RecentKeywordListResponse

    suspend fun getPopularKeywordList(): PopularKeywordListResponse

    suspend fun addRecentKeywordList()

    suspend fun deleteRecentKeywordList()
}