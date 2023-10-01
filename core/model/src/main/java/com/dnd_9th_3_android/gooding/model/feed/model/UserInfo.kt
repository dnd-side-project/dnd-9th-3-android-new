package com.dnd_9th_3_android.gooding.model.feed.model

import com.dnd_9th_3_android.gooding.model.feed.model.OnBoard

data class UserInfo (
    val id : Int,
    val nickname : String,
    val profileImgUrl : String,
    val onboardYn : String,
    val onboards : List<OnBoard>
)