package com.dnd_9th_3_android.gooding.api.myApi.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dnd_9th_3_android.gooding.model.file.FileData
import com.dnd_9th_3_android.gooding.model.feed.model.UserData

@Entity(tableName = "myRecords")
class MyRecordEntity (
    @PrimaryKey val id : Int,
    val title : String,
    val description : String,
    val thumbnailUrl : String,
    val recordDate : String,
    val placeTitle : String,
    val placeLatitude : Double,
    val placeLongitude : Double,
    val recordScore : Int,
    val recordOpen : String,
    val interestType : String,
    val user : UserData,
    val files : List<FileData>,
)