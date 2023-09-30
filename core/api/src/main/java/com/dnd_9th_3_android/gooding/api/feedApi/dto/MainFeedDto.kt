package com.dnd_9th_3_android.gooding.api.feedApi.dto


import com.dnd_9th_3_android.gooding.model.feed.model.FeedFile
import com.dnd_9th_3_android.gooding.model.feed.model.UserInfo

data class MainFeedDto (
    val recordId :Int,
    val title : String,
    val description : String,
    val placeTitle : String,
    val placeLatitude : Int,
    val placeLongitude : Int,
    val thumbnailUrl : String,
    val interestType : String,
    val user : UserInfo,
    val files : List<FeedFile>,
)