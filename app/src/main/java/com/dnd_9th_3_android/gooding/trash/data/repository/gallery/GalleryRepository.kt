package com.dnd_9th_3_android.gooding.trash.data.repository.gallery

import androidx.paging.PagingData
import com.dnd_9th_3_android.gooding.trash.data.model.gallery.GalleryAlbumData
import com.dnd_9th_3_android.gooding.trash.data.model.gallery.GalleryData
import kotlinx.coroutines.flow.Flow

interface GalleryRepository {
    fun getGalleryPagingList(albumName: String): Flow<PagingData<GalleryData>>

    fun getAlbumList(): List<GalleryAlbumData>
}