package com.dnd_9th_3_android.gooding.trash.data.api

import com.dnd_9th_3_android.gooding.trash.data.model.search.PopularKeywordListResponse
import com.dnd_9th_3_android.gooding.trash.data.model.search.RecentKeywordListResponse
import com.dnd_9th_3_android.gooding.trash.data.model.search.SearchFeedListResponse
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface SearchFeedService {
    @GET("v2/local/search/address")
    suspend fun searchFeedList(
        @Query("page") page: Int,
        @Query("query") query: String  // 검색 키워드
    ): SearchFeedListResponse

    @GET("v2/local/search/address")
    suspend fun getRecentKeywordList(
    ): RecentKeywordListResponse

    @GET("v2/local/search/address")
    suspend fun getPopularKeywordList(
    ): PopularKeywordListResponse

    @POST("v2/local/search/address")
    suspend fun addRecentKeywordList(
    ): RecentKeywordListResponse

    @DELETE("v2/local/search/address")
    suspend fun deleteRecentKeywordList(
    ): RecentKeywordListResponse
}