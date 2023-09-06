package com.dnd_9th_3_android.gooding.login.data

import android.content.Context
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.user.model.AccessTokenInfo

interface KaKaoLoginInterface {
    // message
    fun toastMessage(context: Context, message:String)
    // callback init
    fun initCallback(error:Throwable?,token:OAuthToken?,context: Context,loginCallback:(String?)->Unit)
    // 카카오톡 앱으로 로그인
    fun kaKaoLogin(context:Context,callback : (OAuthToken?, Throwable?)  -> Unit,loginCallback:(String?)->Unit)
    // 현재 로그인 상태 확인
    fun checkLogin() : Boolean
    // 카카오 계정 정보 얻기
    fun getUserInfo(context: Context,loginCallback:(AccessTokenInfo?)->Unit)
}