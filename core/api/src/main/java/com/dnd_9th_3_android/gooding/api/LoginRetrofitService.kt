package com.dnd_9th_3_android.gooding.api

import com.dnd_9th_3_android.gooding.model.user.AccessToken
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
interface LoginRetrofitService {
    // for kakao login access token
    @GET("oauth/kakao")
    fun loginKaKao(
        @Query("accessToken")accessToken : String,
    ) : Call<AccessToken>

    // for google login access token
    @GET("oauth/google")
    fun loginGoogle(
        @Query("idToken")idToken : String,
    ) : Call<AccessToken>

    // for get token
    @GET("api/v1/tokens/temporary")
    fun getTemporaryToken() : Call<AccessToken>
}