package com.dnd_9th_3_android.gooding.data.video

import android.util.Log

object CheckUrl {
    fun isVideo(url : String): Boolean{
        val lastIndex = url.lastIndexOf('.')
        val extension: String = url.substring(lastIndex+1)


        return extension == "mp4" || extension == "MP4" ||
                extension == "MOV" || extension == "mov" ||
                extension == "AVI" || extension == "avi" ||
                extension == "MKV" || extension == "mkv" ||
                extension == "WMV" || extension == "wmv" ||
                extension == "TS" || extension == "ts" ||
                extension == "TP" || extension == "tp" ||
                extension == "FLV" || extension == "flv" ||
                extension == "3GP" || extension == "3gp" ||
                extension == "MPG" || extension == "mpg" ||
                extension == "MPEG" || extension == "mpeg" ||
                extension == "MPE" || extension == "mpe" ||
                extension == "ASF" || extension == "asf" ||
                extension == "ASX" || extension == "asx" ||
                extension == "DAT" || extension == "dat" ||
                extension == "RM" || extension == "rm" || extension=="m3u8"
    }
}