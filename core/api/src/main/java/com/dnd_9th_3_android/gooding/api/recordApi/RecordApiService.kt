package com.dnd_9th_3_android.gooding.api.recordApi

import com.dnd_9th_3_android.gooding.api.myApi.entity.MyRecordEntity
import com.dnd_9th_3_android.gooding.model.feed.MyFeed
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RecordApiService {
    // 내 모든 기록
    @GET("api/v1/record/my-record")
    fun getMyRecords(
        @Query("userId") userId: Int,
    ) : Call<ArrayList<MyFeed>>

    // 날짜 데이터 얻기 + paging
    @GET("api/v1/record/date")
    fun getMyDateRecords(
        @Query("userId") userId: Int,
        @Query("recordDate") recordDate: String,
    ) : List<MyRecordEntity>
}