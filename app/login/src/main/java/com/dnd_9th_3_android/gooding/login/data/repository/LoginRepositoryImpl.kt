package com.dnd_9th_3_android.gooding.login.data.repository

import android.content.Context
import android.content.Intent
import androidx.compose.ui.platform.LocalContext
import com.dnd_9th_3_android.gooding.api.NetworkManager
import com.dnd_9th_3_android.gooding.api.UserInfoSharedPreferences
import com.dnd_9th_3_android.gooding.login.data.domain.LoginRepository
import com.dnd_9th_3_android.gooding.model.user.AccessToken
import com.dnd_9th_3_android.gooding.model.user.Category
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
            null -> false
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

    override fun setUserOnBoarding(
        interestCodes : List<String>,
        result: (UserData?) -> Unit
    ){
        networkManager.getUserData().let{ user ->
            if (user!=null) {
                manager.saveUserOnBoarding(
                    user.id,
                    user.nickname,
                    interestCodes
                ).enqueue(object : Callback<UserData>{
                    override fun onResponse(call: Call<UserData>, response: Response<UserData>) {
                        result(response.body())
                    }

                    override fun onFailure(call: Call<UserData>, t: Throwable) {
                        result(null)
                    }

                })
            }else{ result(null) }
        }
    }

    override fun accessMainActivity() {
        // main으로 이동
        val intent = Intent(
            context, Class.forName("com.dnd_9th_3_android.gooding.MainActivity")
        )
        context.startActivity(intent)
    }
}