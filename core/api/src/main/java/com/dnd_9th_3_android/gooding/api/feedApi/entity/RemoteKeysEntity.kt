package com.dnd_9th_3_android.gooding.api.feedApi.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remoteKey")
data class RemoteKeysEntity(
    @PrimaryKey
    val repoId : Int,
    val prevKey : Int?,
    val nextKey : Int?
)