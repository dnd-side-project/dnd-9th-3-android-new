package com.dnd_9th_3_android.gooding.trash.data.model.request

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UploadRequest(
    val title: String,
    val description: String,
    val recordDate: String,
    val placeTitle: String,
    val placeLatitude: Double,
    val placeLongitude: Double,
    val recordOpen: Int,
    val recordScore: String,
): Parcelable