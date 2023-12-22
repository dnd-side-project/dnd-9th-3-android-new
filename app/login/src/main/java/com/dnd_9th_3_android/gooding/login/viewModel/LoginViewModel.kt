package com.dnd_9th_3_android.gooding.login.viewModel

import android.content.Intent
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.lifecycle.ViewModel
import com.dnd_9th_3_android.gooding.login.clientId
import com.dnd_9th_3_android.gooding.login.data.domain.GoogleLoginInterface
import com.dnd_9th_3_android.gooding.login.data.domain.KaKaoLoginInterface
import com.dnd_9th_3_android.gooding.login.data.domain.LoginRepository
import com.dnd_9th_3_android.gooding.data.type.CategoryListType
import com.dnd_9th_3_android.gooding.model.user.AccessToken
import com.dnd_9th_3_android.gooding.model.user.Category
import com.dnd_9th_3_android.gooding.model.user.UserData
import com.google.firebase.auth.FirebaseAuth
import com.kakao.sdk.auth.model.OAuthToken
//import com.dnd_9th_3_android.gooding.data.type.CategoryListType
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
     private val kaKaoLogin : KaKaoLoginInterface,
     private val googleLogin : GoogleLoginInterface,
     private val loginRepository: LoginRepository
): ViewModel() {
    init { loginRepository.categoryList = CategoryListType().categoryImageList }

    fun setCallback(
        error: Throwable?,
        token: OAuthToken?,
        loginState : (UserData?)->Unit,
    ){
        kaKaoLogin.initCallback(error,token,loginCallback={
            if (it!=null){
                kaKaoLogin.loginRequest(it, result = {userToken->
                    if (userToken!=null){
                        loginUser(userToken,loginState = {state -> loginState(state)})
                    } else {loginState(null)}
                })
            } else {loginState(null)}
        })
    }

    fun kaKaoLogin(
        callback : (OAuthToken?, Throwable?)  -> Unit,
        loginState : (String?)->Unit,
    ){
        kaKaoLogin.kaKaoLogin(
            callback,
            loginCallback = { loginState(it) }
        )
    }

    fun checkKaKaoLogin(): Boolean{
        return kaKaoLogin.checkLogin()
    }

    fun setLauncher(
        result: ActivityResult,
        firebaseAuth : FirebaseAuth,
        loginState : (UserData?)->Unit
    ){
        googleLogin.setLauncher(result,firebaseAuth, loginCallback = {
            if (it!=null){
                googleLogin.loginRequest(it, result = {userToken->
                    if (userToken!=null){
                        loginUser(userToken,loginState = {state -> loginState(state)})
                    }  else {loginState(null)}
                })
            }  else {loginState(null)}
        })
    }

    fun goggleLogin(launcher: ActivityResultLauncher<Intent>){
        googleLogin.login(clientId,launcher)
    }

    private fun loginUser(userToken : AccessToken,loginState : (UserData?)->Unit ){
        loginRepository.setUserInfoData(userToken, result = { loginState(it) })
    }

    private fun inMainActivity(){
        loginRepository.accessMainActivity()
    }

    fun checkUser() : Boolean?{
        return loginRepository.checkOnBoarding()
    }

    fun getCheckCategorySize() : Int{
        return loginRepository.checkCategoryCount
    }
    fun getCategoryList() : List<Category>{
        return loginRepository.categoryList
    }
    fun getCategoryListSize() : Int{
        return loginRepository.categoryList.size
    }

    fun plusCheckCategory(){
        if (loginRepository.checkCategoryCount<9)
            loginRepository.checkCategoryCount+=1
    }
    fun minusCheckCategory(){
        if (loginRepository.checkCategoryCount>0)
            loginRepository.checkCategoryCount-=1
    }

    fun setUserName(name : String){
        loginRepository.username = name
    }

    fun getUserName() : String {
        return loginRepository.username
    }

    fun setCategoryState(index : Int){
        loginRepository.categoryList[index].selected =
            !loginRepository.categoryList[index].selected
    }

    private fun getCategoryState() : List<String>{
        val dataList = arrayListOf<String>()
        loginRepository.categoryList.forEach {
            if(it.selected) dataList.add(it.index.toString())
        }
        return dataList.toList()
    }

    fun recordUserOnBoarding(){
        loginRepository.recordOnBoarding(getCategoryState(), result = {})
    }
}