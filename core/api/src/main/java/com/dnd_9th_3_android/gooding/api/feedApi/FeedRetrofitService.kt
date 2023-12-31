package com.dnd_9th_3_android.gooding.api.feedApi

import com.dnd_9th_3_android.gooding.api.feedApi.dto.MainFeedDto
import com.dnd_9th_3_android.gooding.api.feedApi.entity.GetMainFeedEntityList
import com.dnd_9th_3_android.gooding.api.feedApi.entity.MainFeedEntity
import com.dnd_9th_3_android.gooding.model.feed.GetMainFeedList
import com.dnd_9th_3_android.gooding.model.user.OnBoardingData
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface FeedRetrofitService {
    // 관심사 기반 데이터 조회
    @GET("api/v1/feed/{userId}")
    suspend fun getUserFeedFromId(
        @Path("userId") userId : Int?,
        @Query("interestCodes") interestCodes : List<String>?,
        @Query("page") page : Int,
        @Query("size") size : Int,
    ) : GetMainFeedEntityList
}