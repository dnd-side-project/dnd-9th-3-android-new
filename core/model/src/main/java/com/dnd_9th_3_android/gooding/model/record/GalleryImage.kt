package com.dnd_9th_3_android.gooding.model.record

import android.net.Uri

data class GalleryImage(
    val id : Long,
    val filePath : String,
    val uri : Uri,
    val name : String,
    val date : String,
    val size : Int,
    val isSelected : Boolean = false,
)
