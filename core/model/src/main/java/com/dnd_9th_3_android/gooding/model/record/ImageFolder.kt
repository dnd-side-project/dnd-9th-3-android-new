package com.dnd_9th_3_android.gooding.model.record

import android.content.ContentUris
import android.net.Uri
import android.provider.MediaStore

class ImageFolder(
    val name : String,
    val imageCount : Int,
    val firstImage : String?,
    val uriPath : String
)