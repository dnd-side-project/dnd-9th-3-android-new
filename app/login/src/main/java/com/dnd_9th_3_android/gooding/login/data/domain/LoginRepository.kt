package com.dnd_9th_3_android.gooding.login.data.domain

import android.content.Context
import com.dnd_9th_3_android.gooding.model.user.AccessToken
import com.dnd_9th_3_android.gooding.model.user.Category
import com.dnd_9th_3_android.gooding.model.user.UserData

interface LoginRepository {
    var username : String
    var categoryList : List<Category>
    var checkCategoryCount : Int


    fun checkOnBoarding() : Boolean?

    fun setUserInfoData(
        accessToken: AccessToken,
        result : (UserData?) -> Unit
    )

    fun setUserOnBoarding(
        interestCodes : List<String>,
        result: (UserData?) -> Unit
    )

    fun accessMainActivity()

}