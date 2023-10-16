package com.dnd_9th_3_android.gooding.data.dataMy.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dnd_9th_3_android.gooding.api.myApi.entity.MyRecordEntity
import com.dnd_9th_3_android.gooding.api.remoteKey.RemoteKeysEntity
import com.dnd_9th_3_android.gooding.data.converter.FileListConverters
import com.dnd_9th_3_android.gooding.data.converter.UserStringConverters
import com.dnd_9th_3_android.gooding.data.dataMy.local.MyRecordDao
import com.dnd_9th_3_android.gooding.data.di.remoteKey.RemoteKeysDao


@Database(entities = [MyRecordEntity::class,RemoteKeysEntity::class], version = 1)
@TypeConverters(
    value = [
        FileListConverters::class,
        UserStringConverters::class
    ]
)
abstract class MyRecordDatabase : RoomDatabase() {

    abstract fun getMyRecordDao() : MyRecordDao

    abstract fun remoteKeyDao() : RemoteKeysDao
}