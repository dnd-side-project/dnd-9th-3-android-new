package com.dnd_9th_3_android.gooding.trash.data.remote.feed

import com.dnd_9th_3_android.gooding.trash.data.model.feed.UploadFeedResponse
import com.dnd_9th_3_android.gooding.trash.data.model.search.RequestUploadFeed


interface UploadFeedRemoteDataSource {
    suspend fun uploadFeed(request: RequestUploadFeed): UploadFeedResponse
}