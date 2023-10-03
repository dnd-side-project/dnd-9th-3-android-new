package com.dnd_9th_3_android.gooding.data.di

import com.dnd_9th_3_android.gooding.api.NetworkManager
import com.dnd_9th_3_android.gooding.data.dataFeed.database.MainFeedDatabase
import com.dnd_9th_3_android.gooding.data.dataFeed.repository.MainFeedRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object FeedModule {

    @ViewModelScoped
    @Provides
    fun provideFeedRepository(
        db : MainFeedDatabase,
        networkManager: NetworkManager,
    ) = MainFeedRepository(db,networkManager)

}