package com.dnd_9th_3_android.gooding.trash.di

import com.dnd_9th_3_android.gooding.data.local.GalleryLocalDataSource
import com.dnd_9th_3_android.gooding.data.local.GalleryLocalDataSourceImpl
import com.dnd_9th_3_android.gooding.data.remote.map.KakaoMapAddressRemoteDataSource
import com.dnd_9th_3_android.gooding.data.remote.map.KakaoMapAddressRemoteDataSourceImpl
import com.dnd_9th_3_android.gooding.data.remote.search.SearchFeedListRemoteDataSource
import com.dnd_9th_3_android.gooding.data.remote.search.SearchFeedListRemoteDataSourceImpl
import com.dnd_9th_3_android.gooding.data.remote.feed.UploadFeedRemoteDataSource
import com.dnd_9th_3_android.gooding.data.remote.feed.UploadFeedRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class DataSourceModule {

    @Singleton
    @Binds
    abstract fun bindGalleryLocalDataSource(
        galleryLocalDataSourceImpl: GalleryLocalDataSourceImpl
    ): GalleryLocalDataSource

    @Singleton
    @Binds
    abstract fun bindSearchFeedListRemoteDataSource(
        searchFeedListRemoteDataSourceImpl: SearchFeedListRemoteDataSourceImpl
    ): SearchFeedListRemoteDataSource

    @Singleton
    @Binds
    abstract fun bindUploadFeedRemoteDataSource(
        uploadFeedRemoteDataSourceImpl: UploadFeedRemoteDataSourceImpl
    ): UploadFeedRemoteDataSource

    @Singleton
    @Binds
    abstract fun bindKakaoMapAddressRemoteDataSource(
        kakaoMapAddressRemoteDataSourceImpl: KakaoMapAddressRemoteDataSourceImpl
    ): KakaoMapAddressRemoteDataSource
}