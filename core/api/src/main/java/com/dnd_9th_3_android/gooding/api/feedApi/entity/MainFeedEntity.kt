package com.dnd_9th_3_android.gooding.api.feedApi.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dnd_9th_3_android.gooding.model.file.FileData
import com.dnd_9th_3_android.gooding.model.feed.model.UserData

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
    val user : UserData,
    val files : List<FileData>,
    val readCount : Int = 0, // user가 몇번 접근했는지 확인
    val isNumbering : Boolean  = false, // user가 지수 평가를 했는지 확인
    val recordScore : Int = 0 // 평가 점수
)