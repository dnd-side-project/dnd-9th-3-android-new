package com.dnd_9th_3_android.gooding.trash.presentation.gallery

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GalleryFileUiData(
    val id: Long = -1,
    var isSelected: Boolean = false,
    var selectedNumber: Int = -1,
    val mediaType: Int? = -1, // 사진: 1, 동영상: 3
    val mediaData: String = "",
    val duration: Int = 0 // 동영상 재생시간
): Parcelable