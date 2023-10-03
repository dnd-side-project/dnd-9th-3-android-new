package com.dnd_9th_3_android.gooding.data.utils.video

import android.app.Activity
import android.content.Context
import android.media.MediaMetadataRetriever
import android.net.Uri
import android.util.Log

object VideoDataChanger {
    fun getVideoTime(videoUrl: String?): String {
        if (videoUrl!=null) {
            return try {
                val retriever = MediaMetadataRetriever()
                retriever.setDataSource(videoUrl)
                val time = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION)
                val duration = time!!.toLong() / 1000
                val hours = duration / 3600
                val minutes = (duration - hours * 3600) / 60
                val seconds = duration - (hours * 3600 + minutes * 60)
                if (seconds.toInt()<10){
                    "0$seconds"
                }else {
                    seconds.toString()
                }
            }catch (e:Exception){
                Log.d("e",e.toString())
                "00"
            }
        }
        return "00"
    }
}