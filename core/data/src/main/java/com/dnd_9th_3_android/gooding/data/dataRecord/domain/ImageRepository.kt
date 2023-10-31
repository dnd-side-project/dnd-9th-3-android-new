package com.dnd_9th_3_android.gooding.data.dataRecord.domain

import com.dnd_9th_3_android.gooding.model.record.GalleryImage
import com.dnd_9th_3_android.gooding.model.record.ImageFolder

interface ImageRepository {

    fun getAllPhotos(
        page : Int,
        loadSize : Int,
        currentLocation : String?= null
    ) : MutableList<GalleryImage>

    fun getFolderList() : ArrayList<ImageFolder>

    fun getFirstImage() : String?
    fun makeImageFolder(folder : String) : ImageFolder
}