package com.dnd_9th_3_android.gooding.trash.data.repository.feed

import com.dnd_9th_3_android.gooding.data.model.search.RequestUploadFeed
import com.dnd_9th_3_android.gooding.data.model.feed.UploadFeedResponse

interface UploadFeedRepository {
    suspend fun uploadFeed(request: RequestUploadFeed): UploadFeedResponse
}