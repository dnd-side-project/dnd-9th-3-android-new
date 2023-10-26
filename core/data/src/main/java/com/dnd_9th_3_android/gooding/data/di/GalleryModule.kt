package com.dnd_9th_3_android.gooding.data.di

import com.dnd_9th_3_android.gooding.data.dataRecord.domain.ImageInterface
import com.dnd_9th_3_android.gooding.data.dataRecord.repository.ImageRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class GalleryModule {

    @Binds
    @ViewModelScoped
    abstract fun bindImageRepository(
        imageRepository: ImageRepository
    ) : ImageInterface
}