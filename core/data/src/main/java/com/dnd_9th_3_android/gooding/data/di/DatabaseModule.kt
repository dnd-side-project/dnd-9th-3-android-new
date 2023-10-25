package com.dnd_9th_3_android.gooding.data.di

import android.content.Context
import androidx.room.Room
import com.dnd_9th_3_android.gooding.data.converter.FileListConverters
import com.dnd_9th_3_android.gooding.data.converter.UserStringConverters
import com.dnd_9th_3_android.gooding.data.dataFeed.local.database.MainFeedDatabase
import com.dnd_9th_3_android.gooding.data.dataMy.local.database.MyRecordDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun providesFeedDatabase(@ApplicationContext context : Context) =
        Room.databaseBuilder(
            context,
            MainFeedDatabase::class.java,
            "mainFeedDatabase"
        )
            .addTypeConverter(FileListConverters())
            .addTypeConverter(UserStringConverters())
            .build()

    @Singleton
    @Provides
    fun providesMyDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
            MyRecordDatabase::class.java,
            "myRecordDatabase"
        )
            .addTypeConverter(FileListConverters())
            .addTypeConverter(UserStringConverters())
            .build()

    @Singleton
    @Provides
    fun providesFeedDao(database: MainFeedDatabase) =
        database.getMainFeedDao()

    @Singleton
    @Provides
    fun providesMyDao(database : MyRecordDatabase) =
        database.getMyRecordDao()

    @Singleton
    @Provides
    fun providesRemoteDao(database : MainFeedDatabase) =
        database.remoteKeyDao()
}