package com.dnd_9th_3_android.gooding.data.feedData.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dnd_9th_3_android.gooding.model.feed.model.FeedFile
import com.dnd_9th_3_android.gooding.model.feed.model.UserInfo

@Entity(tableName = "mainFeeds")
data class MainFeedEntity (
    @PrimaryKey val recordId :Int,
    val title : String,
    val description : String,
    val placeTitle : String,
    val placeLatitude : Int,
    val placeLongitude : Int,
    val thumbnailUrl : String,
    val interestType : String,
    val user : UserInfo ,
    val files : List<FeedFile>,
    val readCount : Int, // user가 몇번 접근했는지 확인
    val isNumbering : Boolean, // user가 지수 평가를 했는지 확인
    val recordScore : Int
)