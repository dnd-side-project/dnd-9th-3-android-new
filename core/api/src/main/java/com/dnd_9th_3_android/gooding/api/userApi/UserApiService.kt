package com.dnd_9th_3_android.gooding.api.userApi

import com.dnd_9th_3_android.gooding.api.myApi.entity.MyRecordEntity
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

    // oauthId로 사용자 조회
    @GET("api/v1/user/{oauthId}")
    fun getUserInfo(
        @Path("oauthId") oauthId : String,
    ): Call<UserData>


    // onboarding 추가
    @POST("api/v1/onboard/update/{userId}")
    @FormUrlEncoded
    fun saveUserOnBoarding(
        @Path("userId") userId: Int,
        @Field("nickName") nickName:String,
        @Field("interestCodes") interestCodes : List<String>
    ) : Call<OnBoardingCode>
}
