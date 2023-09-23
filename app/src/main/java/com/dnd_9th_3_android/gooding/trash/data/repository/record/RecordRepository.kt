package com.dnd_9th_3_android.gooding.trash.data.repository.record

import com.dnd_9th_3_android.gooding.data.model.feed.UploadFeedResponse
import kotlinx.coroutines.flow.Flow

interface RecordRepository {
    suspend fun sendFeed(): Flow<UploadFeedResponse>
}