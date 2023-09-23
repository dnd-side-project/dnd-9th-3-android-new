package com.dnd_9th_3_android.gooding.trash.data.model.gallery

import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GalleryImageData(
    val uri: Uri,           // 이미지 Uri
    val name: String,       // 이름
    val fullName: String,   // 확장자를 포함한 이름
    val mimeType: String,   // Mime 형식의 type
    val addedDate: Long,    // 저장된 날짜
    val folder: String,     // 저장된 폴더 명
    val size: Long,         // 파일의 크기
    val width: Int,         // 사진의 너비
    val height: Int         // 사진의 높이
): Parcelable