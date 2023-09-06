package com.dnd_9th_3_android.gooding.data.model.search

import com.dnd_9th_3_android.gooding.data.model.gallery.GalleryData
import com.dnd_9th_3_android.gooding.data.model.request.UploadRequest
import java.io.File

data class RequestUploadFeed1(
    val title: String,
    val description: String,
    val recordDate: String, // 실제로는 LocalDateTime / 예시 : ""2023-08-10T12:28:55.696Z""
    val placeTitle: String?,
    val placeLatitude: Double?,
    val placeLongitude: Double?,
    val recordOpen: String?,
    val interestType: String?,
    val recordScore: Int
)

// 순서는 상관 없음

data class RequestUploadFeed(
    val thumbnail: String,
    val images: List<File>,
    val videos: List<File>
)