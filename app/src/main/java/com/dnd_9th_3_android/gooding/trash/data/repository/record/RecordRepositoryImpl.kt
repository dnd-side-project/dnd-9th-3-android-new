package com.dnd_9th_3_android.gooding.trash.data.repository.record

import com.dnd_9th_3_android.gooding.data.model.feed.UploadFeedResponse
import com.dnd_9th_3_android.gooding.data.model.search.RequestUploadFeed
import com.dnd_9th_3_android.gooding.data.remote.feed.UploadFeedRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RecordRepositoryImpl @Inject constructor(
    private val uploadFeedRemoteDataSource: UploadFeedRemoteDataSource
) : RecordRepository {

    override suspend fun sendFeed(): Flow<UploadFeedResponse> = flow {
        emit(
            uploadFeedRemoteDataSource.uploadFeed(
                RequestUploadFeed(
                    thumbnail = "",
                    images = listOf(),
                    videos = listOf()
                )
            )
        )
    }
}