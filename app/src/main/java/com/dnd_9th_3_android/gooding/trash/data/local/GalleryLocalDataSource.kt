package com.dnd_9th_3_android.gooding.trash.data.local

import android.content.ContentResolver
import com.dnd_9th_3_android.gooding.trash.data.model.gallery.GalleryAlbumData
import com.dnd_9th_3_android.gooding.trash.data.model.gallery.GalleryData
import com.dnd_9th_3_android.gooding.trash.data.model.gallery.GalleryImageData
import com.dnd_9th_3_android.gooding.trash.data.model.gallery.GalleryVideoData


interface GalleryLocalDataSource {
    fun getAllImages(page: Int): List<GalleryImageData>

    fun getAllVideos(page: Int): List<GalleryVideoData>

    fun getImageVideoFromGallery(albumName: String, page: Int, pageSize: Int): List<GalleryData>

    fun getMediaFoldersFromMediaStore(resolver: ContentResolver): List<GalleryAlbumData>
}