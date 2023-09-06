package com.dnd_9th_3_android.gooding.model.feed

import com.dnd_9th_3_android.gooding.model.user.UserInfo

data class GetMainFeed(
    val recordId : Int,
    val title : String,
    val description : String,
    val placeTitle : String,
    val placeLatitude : Int,
    val placeLongitude : Int,
    val interestType : String,
    var recordScore : Int = 0,
    val user : UserInfo,
    val files : ArrayList<FileData>
):java.io.Serializable