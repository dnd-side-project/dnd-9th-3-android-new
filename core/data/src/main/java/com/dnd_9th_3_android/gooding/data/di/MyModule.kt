package com.dnd_9th_3_android.gooding.data.di

import com.dnd_9th_3_android.gooding.api.NetworkManager
import com.dnd_9th_3_android.gooding.data.dataMy.local.database.MyRecordDatabase
import com.dnd_9th_3_android.gooding.data.dataMy.repository.MyRecordRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object MyModule {

    @ViewModelScoped
    @Provides
    fun provideMyRepository(
        db : MyRecordDatabase,
        networkManager: NetworkManager
    ) = MyRecordRepository(db,networkManager)
}