package com.dnd_9th_3_android.gooding.api.remoteKey

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remoteKey")
data class RemoteKeysEntity(
    @PrimaryKey
    val repoId : Int,
    val prevKey : Int?,
    val nextKey : Int?
)