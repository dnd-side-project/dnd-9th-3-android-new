package com.dnd_9th_3_android.gooding.data.di

import com.dnd_9th_3_android.gooding.data.dataRecord.domain.ImageRepository
import com.dnd_9th_3_android.gooding.data.dataRecord.domain.RecordStateRepository
import com.dnd_9th_3_android.gooding.data.dataRecord.repository.ImageRepositoryImpl
import com.dnd_9th_3_android.gooding.data.dataRecord.repository.RecordStateRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class RecordModule {

    @Binds
    @ViewModelScoped
    abstract fun bindRecordRepository(
        recordRepositoryImpl: RecordStateRepositoryImpl
    ) : RecordStateRepository

    @Binds
    @ViewModelScoped
    abstract fun bindImageRepository(
        imageRepositoryImpl: ImageRepositoryImpl
    ) : ImageRepository

}