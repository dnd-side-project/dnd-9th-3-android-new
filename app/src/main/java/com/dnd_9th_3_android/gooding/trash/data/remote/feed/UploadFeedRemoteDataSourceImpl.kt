package com.dnd_9th_3_android.gooding.trash.data.remote.feed

import com.dnd_9th_3_android.gooding.data.api.RecordFeedService
import com.dnd_9th_3_android.gooding.data.model.search.RequestUploadFeed
import com.dnd_9th_3_android.gooding.data.model.feed.UploadFeedResponse
import com.dnd_9th_3_android.gooding.presentation.util.FormDataUtil
import javax.inject.Inject

class UploadFeedRemoteDataSourceImpl @Inject constructor(
    private val recordFeedService: RecordFeedService
): UploadFeedRemoteDataSource {
    override suspend fun uploadFeed(request: RequestUploadFeed): UploadFeedResponse {
        val formThumbnail = FormDataUtil.getBody("thumbnail", request.thumbnail)
        val formImages = FormDataUtil.getBody("images", request.thumbnail)
        val formVideos = FormDataUtil.getBody("videos", request.thumbnail)

//        val uploadFeed = recordFeedService.uploadFeed(
//            formThumbnail,
//            formImages,
//            formVideos
//        )

        return recordFeedService.uploadFeed(formThumbnail, "", formImages, formVideos, "", request)

//        val formImages = FormDataUtil.getImageBody("images", request.images)
//        val formVideos = FormDataUtil.getVideoBody("videos", request.videos)
//
//        val uploadFeed = recordFeedService.uploadFeed(
//            formThumbnail,
//            formImages,
//            formVideos
//        )
//
//        return uploadFeed
    }
}