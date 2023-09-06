package com.dnd_9th_3_android.gooding.data.repository.feed

import com.dnd_9th_3_android.gooding.data.remote.feed.UploadFeedRemoteDataSource
import com.dnd_9th_3_android.gooding.data.model.search.RequestUploadFeed
import com.dnd_9th_3_android.gooding.data.model.feed.UploadFeedResponse
import javax.inject.Inject

class UploadFeedRepositoryImpl @Inject constructor(
    private val uploadFeedRemoteDataSource: UploadFeedRemoteDataSource
): UploadFeedRepository {
    override suspend fun uploadFeed(request: RequestUploadFeed): UploadFeedResponse {
        return uploadFeedRemoteDataSource.uploadFeed(request)
    }
}