package com.dnd_9th_3_android.gooding.api

import com.dnd_9th_3_android.gooding.model.feed.GetMainFeedList
import com.dnd_9th_3_android.gooding.model.feed.MyFeed
import com.dnd_9th_3_android.gooding.model.user.OnBoardingCode
import com.dnd_9th_3_android.gooding.model.user.OnBoardingData
import com.dnd_9th_3_android.gooding.model.user.UserData
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query


interface UserApiService {

    // 내 모든 기록
    @GET("api/v1/record/my-record")
    fun getMyRecords(
        @Query("userId") userId: Int,
    ) : Call<ArrayList<MyFeed>>

    // 날짜 데이터 얻기
    @GET("api/v1/record/date")
    fun getMyDateRecords(
        @Query("userId") userId: Int,
        @Query("recordDate") recordDate: Int,
    ) : Call<ArrayList<MyFeed>>


    // oauthId로 사용자 조회
    @GET("api/v1/user/{oauthId}")
    fun getUserInfo(
        @Path("oauthId") oauthId : String,
    ): Call<UserData>


    @POST("api/v1/onboard/update/{userId}")
    @FormUrlEncoded
    fun saveUserOnBoarding(
        @Path("userId") userId: Int,
        @Field("nickName") nickName:String,
        @Field("interestCodes") interestCodes : List<String>
    ) : Call<OnBoardingCode>


}
