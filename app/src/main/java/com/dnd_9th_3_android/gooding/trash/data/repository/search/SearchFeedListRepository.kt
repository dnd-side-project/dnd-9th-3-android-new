package com.dnd_9th_3_android.gooding.trash.data.repository.search

import androidx.paging.PagingData
import com.dnd_9th_3_android.gooding.trash.data.model.search.PopularKeywordListResponse
import com.dnd_9th_3_android.gooding.trash.data.model.search.RecentKeywordListResponse
import com.dnd_9th_3_android.gooding.trash.data.model.search.SearchFeedData
import kotlinx.coroutines.flow.Flow

interface SearchFeedRepository {
    fun searchFeedRepository(query: String): Flow<PagingData<SearchFeedData>>

    suspend fun getRecentKeywordList(): RecentKeywordListResponse

    suspend fun getPopularKeywordList(): PopularKeywordListResponse

    suspend fun addRecentKeywordList()

    suspend fun deleteRecentKeywordList()
}