package com.dnd_9th_3_android.gooding.data.remote.feed

import com.dnd_9th_3_android.gooding.data.model.search.RequestUploadFeed
import com.dnd_9th_3_android.gooding.data.model.feed.UploadFeedResponse

interface UploadFeedRemoteDataSource {
    suspend fun uploadFeed(request: RequestUploadFeed): UploadFeedResponse
}