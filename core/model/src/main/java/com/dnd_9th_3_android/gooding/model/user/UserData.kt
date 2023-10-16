package com.dnd_9th_3_android.gooding.model.user

data class UserData(
    val id : Int,
    val nickname : String,
    val profileImgUrl : String,
    val onboardYn : String,
    val onboards : List<OnBoardingData>,
    var onboardingStrings : List<String>
) : java.io.Serializable