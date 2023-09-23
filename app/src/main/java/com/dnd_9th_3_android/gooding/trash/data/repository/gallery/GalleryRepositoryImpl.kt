package com.dnd_9th_3_android.gooding.trash.data.repository.gallery

import android.content.Context
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.dnd_9th_3_android.gooding.data.local.GalleryLocalDataSource
import com.dnd_9th_3_android.gooding.data.model.gallery.GalleryAlbumData
import com.dnd_9th_3_android.gooding.data.model.gallery.GalleryData
import com.dnd_9th_3_android.gooding.data.paging.GalleryPagingSource
import dagger.hilt.android.qualifiers.ApplicationContext

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GalleryRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val galleryLocalDataSource: GalleryLocalDataSource
): GalleryRepository {

    override fun getGalleryPagingList(
        albumName: String
    ): Flow<PagingData<GalleryData>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                initialLoadSize = 20
            ),
            pagingSourceFactory = {
                GalleryPagingSource(galleryLocalDataSource, albumName)
            }
        )
            .flow
    }

    override fun getAlbumList(): List<GalleryAlbumData> {
        return galleryLocalDataSource.getMediaFoldersFromMediaStore(context.contentResolver)
    }
}