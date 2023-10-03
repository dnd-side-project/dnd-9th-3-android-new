package com.dnd_9th_3_android.gooding.data.utils.video

import android.graphics.Bitmap
import android.media.MediaMetadataRetriever
import android.net.Uri

object VideoThumbnailUtil {
    //영상의 1초 시간의 사진을 가져옴
    val thumbnailTime = 1

    fun getWebVideoThumbnail(uri : String) : Bitmap? {
        val retriever = MediaMetadataRetriever()

        try {
            retriever.setDataSource(uri, HashMap<String,String>())
            return retriever.getFrameAtTime((thumbnailTime * 1000000).toLong(), MediaMetadataRetriever.OPTION_CLOSEST)
        } catch (e : IllegalArgumentException){
            e.printStackTrace()
        } catch (e : RuntimeException){
            e.printStackTrace()
        } finally {
            try {
                retriever.release()
            } catch (e : RuntimeException){
                e.printStackTrace()
            }
        }
        return null
    }
}