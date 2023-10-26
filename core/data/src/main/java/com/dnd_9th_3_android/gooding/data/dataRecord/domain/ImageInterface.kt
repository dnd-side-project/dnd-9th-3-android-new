package com.dnd_9th_3_android.gooding.data.dataRecord.domain

import com.dnd_9th_3_android.gooding.model.record.GalleryImage

interface ImageInterface {

    fun getAllPhotos(
        page : Int,
        loadSize : Int,
        currentLocation : String?= null
    ) : MutableList<GalleryImage>

    fun getFolderList() : ArrayList<String>
}