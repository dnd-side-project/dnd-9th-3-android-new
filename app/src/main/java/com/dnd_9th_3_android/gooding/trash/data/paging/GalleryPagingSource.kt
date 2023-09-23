package com.dnd_9th_3_android.gooding.trash.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.dnd_9th_3_android.gooding.data.local.GalleryLocalDataSource
import com.dnd_9th_3_android.gooding.data.model.gallery.GalleryData

class GalleryPagingSource(
    private val galleryLocalDataSource: GalleryLocalDataSource,
    private val albumName: String
) : PagingSource<Int, GalleryData>() {

    override fun getRefreshKey(state: PagingState<Int, GalleryData>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GalleryData> {
        val page = params.key ?: FIRST_PAGE
        val data = galleryLocalDataSource.getImageVideoFromGallery(albumName, page, params.loadSize)

        return LoadResult.Page(
            data = data,
            prevKey = null,
            nextKey = if (data.isNotEmpty()) page + 1 else null
        )
    }

    companion object {
        const val FIRST_PAGE = 0
    }
}