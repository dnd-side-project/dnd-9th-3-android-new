package com.dnd_9th_3_android.gooding.data.feedData.di

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import com.dnd_9th_3_android.gooding.api.NetworkManager
import com.dnd_9th_3_android.gooding.data.feedData.database.MainFeedDatabase
import com.dnd_9th_3_android.gooding.data.feedData.local.MainFeedDao
import com.dnd_9th_3_android.gooding.data.feedData.repository.MainFeedRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
object FeedModule {

    @Provides
    @Singleton
    fun providesDatabase(@ApplicationContext context : Application) =
        Room.databaseBuilder(
            context,
            MainFeedDatabase::class.java,
            "mainFeedDatabase"
        ).build()

    @Provides
    @Singleton
    fun providesDao(database: MainFeedDatabase) =
        database.getMainFeedDao()

    @Provides
    @Singleton
    fun providesRemoteDao(database : MainFeedDatabase) =
        database.remoteKeyDao()


    @ViewModelScoped
    @Provides
    fun provideFeedRepository(
        db : MainFeedDatabase,
        networkManager: NetworkManager
    ) = MainFeedRepository(db,networkManager)

}