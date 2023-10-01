package com.dnd_9th_3_android.gooding.data.feedData.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dnd_9th_3_android.gooding.api.feedApi.entity.MainFeedEntity
import com.dnd_9th_3_android.gooding.data.feedData.local.MainFeedDao
import com.dnd_9th_3_android.gooding.data.feedData.local.RemoteKeysDao

@Database(entities = [MainFeedEntity::class]
    , version = 1,exportSchema = false)
abstract class MainFeedDatabase : RoomDatabase() {

    abstract fun getMainFeedDao() : MainFeedDao

    abstract fun remoteKeyDao() : RemoteKeysDao
}