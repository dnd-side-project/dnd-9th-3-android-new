package com.dnd_9th_3_android.gooding.presentation.gallery

import android.net.Uri

data class GalleryAlbumUiData(
    val thumbnail: Uri,
    val folderName: String,
    val folderFileCount: Int
)