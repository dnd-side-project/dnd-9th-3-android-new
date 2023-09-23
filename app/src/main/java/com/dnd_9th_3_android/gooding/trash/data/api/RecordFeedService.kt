package com.dnd_9th_3_android.gooding.trash.data.api


import com.dnd_9th_3_android.gooding.trash.data.model.feed.UploadFeedResponse
import com.dnd_9th_3_android.gooding.trash.data.model.search.RequestUploadFeed
import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface RecordFeedService {
    @Multipart
    @POST("api/v1/record/upload")
    suspend fun uploadFeed(
        @Part thumbnail: MultipartBody.Part,
        @Part thumbnailDirectory: String,
        @Part images: MultipartBody.Part,
        @Part videos: MultipartBody.Part,
        @Part oauthId: String,
        @Part uploadRequest: RequestUploadFeed
    ): UploadFeedResponse
}