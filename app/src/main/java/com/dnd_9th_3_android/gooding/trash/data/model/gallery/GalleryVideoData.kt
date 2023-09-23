package com.dnd_9th_3_android.gooding.trash.data.model.gallery

import android.graphics.Bitmap
import android.media.MediaMetadataRetriever
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GalleryVideoData(
    val title: String?,     // 동영상 제목
    val path: String?,      // 파일 경로
    val mimeType: String?,  // Mime 형식의 type
    val duration: Long,     // 재생 시간
    val addedDate: Long,    // 저장된 날짜
    val folder: String,     // 저장된 폴더 명
    val size: Long,         // 파일 크기
    val width: Int,         // 동영상의 너비
    val height: Int         // 동영상의 높이
): Parcelable {
    // 동영상의 썸네일을 반환한다.
    private fun getThumbnail(filePath: String): Bitmap? {
        val thumbnailTime = 1
        val retriever = MediaMetadataRetriever()

        retriever.setDataSource(filePath, HashMap<String,String>())

        return retriever.getFrameAtTime((thumbnailTime * 1000000).toLong(), MediaMetadataRetriever.OPTION_CLOSEST)
    }
}