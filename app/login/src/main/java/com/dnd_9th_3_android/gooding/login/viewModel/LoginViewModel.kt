package com.dnd_9th_3_android.gooding.login.viewModel

import android.content.Intent
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.dnd_9th_3_android.gooding.api.UserLoginInfo
import com.dnd_9th_3_android.gooding.login.clientId
import com.dnd_9th_3_android.gooding.login.data.domain.GoogleLoginInterface
import com.dnd_9th_3_android.gooding.login.data.domain.KaKaoLoginInterface
import com.dnd_9th_3_android.gooding.login.data.domain.LoginRepository
import com.dnd_9th_3_android.gooding.login.type.CategoryListType
import com.dnd_9th_3_android.gooding.model.user.AccessToken
import com.google.firebase.auth.FirebaseAuth
import com.kakao.sdk.auth.model.OAuthToken
//import com.dnd_9th_3_android.gooding.login.type.CategoryListType
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
     private val kaKaoLogin : KaKaoLoginInterface,
     private val googleLogin : GoogleLoginInterface,
     private val loginRepository: LoginRepository
): ViewModel() {
    private var navController : NavHostController? = null
    init { loginRepository.categoryList = CategoryListType().categoryImageList }

    fun initNavi(navi: NavHostController){
        navController = navi
    }
    fun setCallback(
        error: Throwable?,
        token: OAuthToken?,
    ){
        kaKaoLogin.initCallback(error,token,loginCallback={
            if (it!=null){
                kaKaoLogin.loginRequest(it, result = {userToken->
                    if (userToken!=null){
                        loginUser(userToken)
                    }
                })
            }
        })
    }

    fun kaKaoLogin(
        callback : (OAuthToken?, Throwable?)  -> Unit,
    ){
        kaKaoLogin.kaKaoLogin(
            callback,
            loginCallback = {}
        )
    }

    fun checkKaKaoLogin(): Boolean{
        return kaKaoLogin.checkLogin()
    }

    fun setLauncher(
        result: ActivityResult,
        firebaseAuth : FirebaseAuth,
    ){
        googleLogin.setLauncher(result,firebaseAuth, loginCallback = {
            if (it!=null){
                googleLogin.loginRequest(it, result = {userToken->
                    if (userToken!=null){
                        loginUser(userToken)
                    }
                })
            }
        })
    }

    fun goggleLogin(launcher: ActivityResultLauncher<Intent>){
        googleLogin.login(clientId,launcher)
    }

    fun loginUser(userToken : AccessToken){
        loginRepository.setUserInfoData(userToken, result = {
            if (it!=null){
                inOnBoarding()
            }
        })
    }

    private fun inOnBoarding(){
        navController?.navigate("onBoardingScreen")
    }

    fun checkUser() : Boolean?{
        return loginRepository.checkOnBoarding()
    }



}