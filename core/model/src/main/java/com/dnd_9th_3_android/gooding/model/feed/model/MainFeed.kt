package com.dnd_9th_3_android.gooding.model.feed.model

data class MainFeed (
    val recordId :Int,
    val title : String,
    val description : String,
    val placeTitle : String,
    val placeLatitude : Int,
    val placeLongitude : Int,
    val thumbnailUrl : String,
    val interestType : String,
    val user : UserData,
    val files : List<FeedData>,
    val readCount : Int = 0, // user가 몇번 접근했는지 확인
    val isNumbering : Boolean = false, // user가 지수 평가를 했는지 확인
    var recordScore : Int = 0,
)