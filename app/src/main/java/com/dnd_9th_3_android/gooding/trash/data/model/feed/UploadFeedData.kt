package com.dnd_9th_3_android.gooding.trash.data.model.feed

import android.os.Parcelable
import com.dnd_9th_3_android.gooding.data.model.request.UploadRequest
import kotlinx.parcelize.Parcelize
import java.io.File

//@Parcelize
//data class UploadFeedData(
//    val title: String,
//    val description: String,
//    val recordDate: String, // 실제로는 LocalDateTime / 예시 : ""2023-08-10T12:28:55.696Z""
//    val placeTitle: String,
//    val placeLatitude: Double,
//    val placeLongitude: Double,
//    val recordOpen: String,
//    val interestType: String,
//    val recordScore: Int
//) : Parcelable


@Parcelize
data class UploadFeedData(
    val thumbnail: String,
    val thumbnailDirectory: String,
    val images: List<File>, // 실제로는 LocalDateTime / 예시 : ""2023-08-10T12:28:55.696Z""
    val videos: List<File>,
    val oauthId: String,
    val uploadRequest: UploadRequest
) : Parcelable