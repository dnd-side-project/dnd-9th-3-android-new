package com.dnd_9th_3_android.gooding.login.data.domain

import android.content.Context
import com.dnd_9th_3_android.gooding.model.user.AccessToken
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.user.model.AccessTokenInfo

interface KaKaoLoginInterface {
    // message
    fun toastMessage( message:String)
    // callback init
    fun initCallback(error:Throwable?,token:OAuthToken?,loginCallback:(String?)->Unit)
    // 카카오톡 앱으로 로그인
    fun kaKaoLogin(callback : (OAuthToken?, Throwable?)  -> Unit,loginCallback:(String?)->Unit)
    // 현재 로그인 상태 확인
    fun checkLogin() : Boolean
    // 카카오 계정 정보 얻기
    fun getUserInfo(loginCallback:(AccessTokenInfo?)->Unit)

    fun loginRequest(accessToken:String,result:(AccessToken?)->Unit)
}