package com.dnd_9th_3_android.gooding.data.di

import com.dnd_9th_3_android.gooding.data.dataRecord.domain.ImageRepository
import com.dnd_9th_3_android.gooding.data.dataRecord.repository.ImageRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class GalleryModule {

    @Binds
    @ViewModelScoped
    abstract fun bindImageRepository(
        imageRepositoryImpl: ImageRepositoryImpl
    ) : ImageRepository
}