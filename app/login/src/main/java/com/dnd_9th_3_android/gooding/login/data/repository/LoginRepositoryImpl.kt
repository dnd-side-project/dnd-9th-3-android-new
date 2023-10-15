package com.dnd_9th_3_android.gooding.login.data.repository

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.compose.ui.platform.LocalContext
import com.dnd_9th_3_android.gooding.api.NetworkManager
import com.dnd_9th_3_android.gooding.api.UserInfoSharedPreferences
import com.dnd_9th_3_android.gooding.login.data.domain.LoginRepository
import com.dnd_9th_3_android.gooding.model.user.AccessToken
import com.dnd_9th_3_android.gooding.model.user.Category
import com.dnd_9th_3_android.gooding.model.user.OnBoardingCode
import com.dnd_9th_3_android.gooding.model.user.UserData
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val networkManager: NetworkManager,
) : LoginRepository{
    override var username: String = ""
    override var categoryList: List<Category> = listOf()
    override var checkCategoryCount: Int = 0

    private val manager = networkManager.getUserApiService()

    override fun checkOnBoarding(): Boolean? {
        return when (networkManager.getUserData()?.onboardYn){
            "yes"-> true
            "no" -> false
            else -> null
        }
    }

    override fun setUserInfoData(
        accessToken: AccessToken,
        result : (UserData?) -> Unit
    ){
        // token
        UserInfoSharedPreferences(context).apply {
            this.userOauth = accessToken.oauthId
            this.accessToken = accessToken.accessToken
        }
        // user info
        manager.getUserInfo(accessToken.oauthId)
            .enqueue(object : Callback<UserData> {
                override fun onResponse(
                    call: Call<UserData>,
                    response: Response<UserData>
                ) {
                    response.body()?.let { networkManager.setUserData(it) }
                    result(response.body())
                }

                override fun onFailure(call: Call<UserData>, t: Throwable) {
                    result(null)
                }

            })
    }


    override fun accessMainActivity() {
        // main으로 이동
        val intent = Intent(
            context, Class.forName("com.dnd_9th_3_android.gooding.MainActivity")
        )
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }

    override fun recordOnBoarding(categoryState : List<String>,result: (OnBoardingCode?) -> Unit) {
        networkManager.getUserData()?.id?.let {userId->
            manager.saveUserOnBoarding(
                userId = userId,
                nickName = username,
                interestCodes = categoryState
            ).enqueue(object : Callback<OnBoardingCode>{
                override fun onResponse(
                    call: Call<OnBoardingCode>,
                    response: Response<OnBoardingCode>
                ) { result(response.body()) }
                override fun onFailure(call: Call<OnBoardingCode>, t: Throwable) { result(null) }
            })
        }
    }
}