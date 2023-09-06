package com.dnd_9th_3_android.gooding.data.repository.search

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.dnd_9th_3_android.gooding.data.paging.SearchFeedListPagingSource
import com.dnd_9th_3_android.gooding.data.remote.search.SearchFeedListRemoteDataSource
import com.dnd_9th_3_android.gooding.data.model.search.PopularKeywordListResponse
import com.dnd_9th_3_android.gooding.data.model.search.RecentKeywordListResponse
import com.dnd_9th_3_android.gooding.data.model.search.SearchFeedData
import com.dnd_9th_3_android.gooding.data.model.search.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SearchFeedRepositoryImpl @Inject constructor(
    private val searchFeedListRemoteDataSource: SearchFeedListRemoteDataSource
): SearchFeedRepository {
    override fun searchFeedRepository(query: String): Flow<PagingData<SearchFeedData>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                initialLoadSize = 20
            ),
            pagingSourceFactory = {
                SearchFeedListPagingSource(searchFeedListRemoteDataSource, query)
            }
        )
            .flow
            .map { feedData ->
                feedData.map {
                    it.toEntity()
                }
            }
    }

    override suspend fun getRecentKeywordList(): RecentKeywordListResponse {
        return searchFeedListRemoteDataSource.getRecentKeywordList()
    }

    override suspend fun getPopularKeywordList(): PopularKeywordListResponse {
        return searchFeedListRemoteDataSource.getPopularKeywordList()
    }

    override suspend fun addRecentKeywordList() {
        TODO("Not yet implemented")
    }

    override suspend fun deleteRecentKeywordList() {
        TODO("Not yet implemented")
    }
}