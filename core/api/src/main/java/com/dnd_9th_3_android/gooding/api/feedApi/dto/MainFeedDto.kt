package com.dnd_9th_3_android.gooding.api.feedApi.dto


import com.dnd_9th_3_android.gooding.model.feed.model.UserData
import com.dnd_9th_3_android.gooding.model.file.FileData

data class MainFeedDto (
    val recordId :Int,
    val title : String,
    val description : String,
    val placeTitle : String,
    val placeLatitude : Int,
    val placeLongitude : Int,
    val thumbnailUrl : String,
    val interestType : String,
    val user : UserData,
    val files : List<FileData>,
)