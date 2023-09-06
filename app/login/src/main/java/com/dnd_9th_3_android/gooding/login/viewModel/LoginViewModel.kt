package com.dnd_9th_3_android.gooding.login.viewModel

import androidx.lifecycle.ViewModel
import com.dnd_9th_3_android.gooding.api.RetrofitUtil
import com.dnd_9th_3_android.gooding.api.UserInfo
//import com.dnd_9th_3_android.gooding.login.type.CategoryListType
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

// for api
@HiltViewModel
class LoginViewModel @Inject constructor(): ViewModel(){
    var isUser  = true
    fun userInfoData(){
        if(isUser) {
            RetrofitUtil.userOauth?.let { oauth ->
                RetrofitUtil.userApiService.getUserInfo(oauth)
                    .enqueue(object : Callback<com.dnd_9th_3_android.gooding.model.user.UserInfo> {
                        override fun onResponse(
                            call: Call<com.dnd_9th_3_android.gooding.model.user.UserInfo>,
                            response: Response<com.dnd_9th_3_android.gooding.model.user.UserInfo>
                        ) {
                            if (response.isSuccessful) {
                                UserInfo.myData = response.body()!!
                            }
                        }

                        override fun onFailure(
                            call: Call<com.dnd_9th_3_android.gooding.model.user.UserInfo>,
                            t: Throwable
                        ) {
                        }

                    })
            }
        }
        isUser = false
    }
}