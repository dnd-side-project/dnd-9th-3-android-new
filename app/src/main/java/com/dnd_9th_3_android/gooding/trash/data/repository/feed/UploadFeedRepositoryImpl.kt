package com.dnd_9th_3_android.gooding.trash.data.repository.feed


import com.dnd_9th_3_android.gooding.trash.data.model.feed.UploadFeedResponse
import com.dnd_9th_3_android.gooding.trash.data.model.search.RequestUploadFeed
import com.dnd_9th_3_android.gooding.trash.data.remote.feed.UploadFeedRemoteDataSource
import javax.inject.Inject

class UploadFeedRepositoryImpl @Inject constructor(
    private val uploadFeedRemoteDataSource: UploadFeedRemoteDataSource
): UploadFeedRepository {
    override suspend fun uploadFeed(request: RequestUploadFeed): UploadFeedResponse {
        return uploadFeedRemoteDataSource.uploadFeed(request)
    }
}