package com.dnd_9th_3_android.gooding.login.data

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.kakao.sdk.auth.AuthApiClient
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.common.model.KakaoSdkError
import com.kakao.sdk.user.UserApiClient
import com.kakao.sdk.user.model.AccessTokenInfo
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

class KaKaoLoginImpl @Inject constructor() : KaKaoLoginInterface {
    override fun initCallback(
        error: Throwable?,
        token: OAuthToken?,
        context: Context,
        loginCallback: (String?) -> Unit
    ) {
        if (error != null){
            toastMessage(context,"카카오 로그인 실패 $error")
            loginCallback(null)
        }else if (token != null){
            // 로그인 성공
            Log.e("token",token.toString())
            loginCallback(token.accessToken)
        }
    }

    override fun toastMessage(context:Context,message:String) {
        Toast.makeText(context,message, Toast.LENGTH_SHORT).show()
    }

    override fun kaKaoLogin(context:Context,callback : (OAuthToken?, Throwable?)  -> Unit,loginCallback: (String?) -> Unit) {
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)){ //앱 설치 상태
            UserApiClient.instance.loginWithKakaoTalk(context){ _, error ->
                if (error != null){
                    Log.d("카카오톡 앱 로그인 실패",error.toString())

                    // 사용자가 카카오톡 설치 후 디바이스 권한 요청 화면에서 로그인 취소의 경우
                    // 의도적 로그인 취소로 판단. 카카오 계정으로 로그인 시도 없이 로그인 취소 처리 (아무런 동작 x)
                    if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                        loginCallback(null)
                        return@loginWithKakaoTalk
                    }

                    // 앱설치 -> 접근 오류 -> 카카오 계정으로 로그인 시도
                    UserApiClient.instance.loginWithKakaoAccount(context, callback =  callback)
                }
            }
        } else { //앱 비설치 상태 -> 카카오 계정으로 로그인 시도
            UserApiClient.instance.loginWithKakaoAccount(context, callback = callback)
        }
    }

    override fun checkLogin(): Boolean {
        // 이미 로그인 상태
        var check = false
        if (AuthApiClient.instance.hasToken()){
            UserApiClient.instance.accessTokenInfo { _, error->
                if (error != null) {
                    if (error is KakaoSdkError && error.isInvalidTokenError()) {
                        check = false
                    } else {
                        // 기타 에러 발생
                        Log.d("kakao Login error : ", error.toString())
                        check = false
                    }
                } else {
                    // 토큰 유효성 체크 성공  ( 필요 시 토큰 재 갱신)
                    check = true
                }
            }
        } else {
            // 토큰이 없음
            check = false
        }
        return check
    }

    override fun getUserInfo(context: Context,loginCallback: (AccessTokenInfo?) -> Unit) {
        UserApiClient.instance.accessTokenInfo{ tokenInfo, error ->
            if (error != null){
                loginCallback(null)
                toastMessage(context,"토큰 정보 불러오기 실패:$error")
            }
            else if (tokenInfo != null){
                loginCallback(tokenInfo) // 로그인 정보와 만료 기간 전송
            }
            else {
                loginCallback(null)
                toastMessage(context,"토큰 정보 불러오기 실패 error")
            }
        }
    }
}