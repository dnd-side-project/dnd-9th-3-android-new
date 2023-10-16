package com.dnd_9th_3_android.gooding.data.dataFeed.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dnd_9th_3_android.gooding.api.feedApi.entity.MainFeedEntity
import com.dnd_9th_3_android.gooding.api.feedApi.entity.RemoteKeysEntity
import com.dnd_9th_3_android.gooding.data.converter.FileListConverters
import com.dnd_9th_3_android.gooding.data.converter.UserStringConverters
import com.dnd_9th_3_android.gooding.data.dataFeed.local.MainFeedDao
import com.dnd_9th_3_android.gooding.data.dataFeed.local.RemoteKeysDao

@Database(entities = [MainFeedEntity::class, RemoteKeysEntity::class]
    , version = 1)
@TypeConverters(
    value = [
        FileListConverters::class,
        UserStringConverters::class
    ]
)
abstract class MainFeedDatabase : RoomDatabase() {

    abstract fun getMainFeedDao() : MainFeedDao

    abstract fun remoteKeyDao() : RemoteKeysDao
}