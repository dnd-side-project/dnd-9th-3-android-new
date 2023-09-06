package com.dnd_9th_3_android.gooding.model.feed

import com.dnd_9th_3_android.gooding.model.user.SampleUserInfo
import java.io.Serializable

data class Feed(
    val subject : String,
    val content : String,
    var romanticPer : Int,
    val location : String,
    val urlList : List<String>,
    val uploadTime : String,
    val userInfo: SampleUserInfo
): Serializable