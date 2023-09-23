package com.dnd_9th_3_android.gooding.trash.data.repository.feed

import com.dnd_9th_3_android.gooding.trash.data.model.feed.UploadFeedResponse
import com.dnd_9th_3_android.gooding.trash.data.model.search.RequestUploadFeed


interface UploadFeedRepository {
    suspend fun uploadFeed(request: RequestUploadFeed): UploadFeedResponse
}