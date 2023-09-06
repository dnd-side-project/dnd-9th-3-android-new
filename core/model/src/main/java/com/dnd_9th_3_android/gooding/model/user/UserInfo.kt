package com.dnd_9th_3_android.gooding.model.user

data class UserInfo(
    val id : Int,
    val nickname : String,
    val profileImgUrl : String,
    val onboardYn : String,
    val onboardings : List<OnBoardingData>
) : java.io.Serializable