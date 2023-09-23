package com.dnd_9th_3_android.gooding.trash.data.local

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.content.ContentUris
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.CancellationSignal
import android.provider.MediaStore
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.net.toUri
import com.dnd_9th_3_android.gooding.R
import com.dnd_9th_3_android.gooding.trash.data.model.gallery.GalleryAlbumData
import com.dnd_9th_3_android.gooding.trash.data.model.gallery.GalleryData
import com.dnd_9th_3_android.gooding.trash.data.model.gallery.GalleryImageData
import com.dnd_9th_3_android.gooding.trash.data.model.gallery.GalleryVideoData
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class GalleryLocalDataSourceImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : GalleryLocalDataSource {

    private val resolver: ContentResolver? = context.contentResolver

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("Range")
    override fun getImageVideoFromGallery(
        albumName: String,
        page: Int,
        pageSize: Int
    ): List<GalleryData> {
        val galleryDataList = mutableListOf<GalleryData>()

        val projection = arrayOf(
            MediaStore.Files.FileColumns._ID,
            MediaStore.Files.FileColumns.DATE_ADDED,
            MediaStore.Files.FileColumns.MEDIA_TYPE,
            MediaStore.Files.FileColumns.DURATION,
            MediaStore.Files.FileColumns.BUCKET_DISPLAY_NAME,
        )

        val selection = "${MediaStore.Files.FileColumns.MEDIA_TYPE} IN (?, ?)"
        val selectionArgs = arrayOf(
            MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE.toString(),
            MediaStore.Files.FileColumns.MEDIA_TYPE_VIDEO.toString()
        )

        val sortOrder = MediaStore.Files.FileColumns.DATE_ADDED + " DESC"
        val offset = page * pageSize

        val bundle = Bundle().apply {
            putInt(ContentResolver.QUERY_ARG_LIMIT, pageSize)
            putInt(ContentResolver.QUERY_ARG_OFFSET, offset)
            putString(ContentResolver.QUERY_ARG_SQL_SELECTION, selection)
            putStringArray(ContentResolver.QUERY_ARG_SQL_SELECTION_ARGS, selectionArgs)
            putString(ContentResolver.QUERY_ARG_SQL_SORT_ORDER, sortOrder)
        }

        val queryUri = MediaStore.Files.getContentUri("external")

        val cursor: Cursor? = resolver?.query(
            queryUri,
            projection,
            bundle,
            CancellationSignal()
        )

        cursor?.let {
            val bucketDisplayNameColumnIndex =
                cursor.getColumnIndex(MediaStore.Files.FileColumns.BUCKET_DISPLAY_NAME)

            while (cursor.moveToNext()) {
                val id = cursor.getLong(cursor.getColumnIndex(MediaStore.Files.FileColumns._ID))
                val mediaType =
                    cursor.getInt(cursor.getColumnIndex(MediaStore.Files.FileColumns.MEDIA_TYPE))
                val folderName = cursor.getString(bucketDisplayNameColumnIndex)

                val galleryData = GalleryData(
                    id = id,
                    mediaType = mediaType,
                    mediaData = if (mediaType == 1) {
                        "content://media/external/images/media/$id"
                    } else {
                        "content://media/external/video/media/$id"
                    },
                    duration = cursor.getInt(cursor.getColumnIndex(MediaStore.Files.FileColumns.DURATION))
                )

                if (folderName == albumName || albumName == context.getString(R.string.recent_album_name) || albumName.isEmpty()) {
                    galleryDataList.add(galleryData)
                }
            }

            cursor.close()
        }

        return galleryDataList
    }

    override fun getAllImages(
        page: Int
    ): List<GalleryImageData> {
        val galleryImageList = mutableListOf<GalleryImageData>()
        // 외장 메모리에 있는 URI를 받도록 함
        val externalContentUri: Uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        // 커서에 가져올 정보에 대해서 지정한다.
        val cursor: Cursor?

        val projection = arrayOf(
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.TITLE,
            MediaStore.Images.Media.DISPLAY_NAME, // 이름
            MediaStore.Images.Media.MIME_TYPE,
            MediaStore.Images.Media.DATE_TAKEN,
            MediaStore.Images.Media.BUCKET_DISPLAY_NAME,
            MediaStore.Images.Media.SIZE, // 크기
            MediaStore.Images.Media.WIDTH,
            MediaStore.Images.Media.HEIGHT,
        )

        val selection: String? =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) MediaStore.Images.Media.SIZE + " > 0" else null
        val selectionArgs: Array<String>? = null

        cursor = resolver?.query(
            externalContentUri,
            projection,
            selection,
            selectionArgs,
            "${MediaStore.Images.ImageColumns.DATE_ADDED} DESC"
        )

        while (cursor?.moveToNext() == true) {
            galleryImageList.add(
                GalleryImageData(
                    uri = ContentUris.withAppendedId(
                        externalContentUri,
                        cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID))
                    ),
                    name = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.TITLE)),
                    fullName = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME)),
                    mimeType = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.MIME_TYPE)),
                    addedDate = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATE_TAKEN)),
                    folder = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME)),
                    size = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.SIZE)),
                    width = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.WIDTH)),
                    height = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.HEIGHT)),
                )
            )

            cursor.close()
        }

        Log.d("TAG", "getAllImages: $galleryImageList")

        return galleryImageList
    }

//    override suspend fun fetchGalleryImages(limit: Int, offset: Int): List<GalleryImageData> {
//        val contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
//        val projection = arrayOf(
//            MediaStore.Images.Media._ID,
//            MediaStore.Images.Media.TITLE,
//            MediaStore.Images.Media.DISPLAY_NAME,
//            MediaStore.Images.Media.MIME_TYPE,
//            MediaStore.Images.Media.DATE_TAKEN,
//            MediaStore.Images.Media.BUCKET_DISPLAY_NAME,
//            MediaStore.Images.Media.SIZE,
//            MediaStore.Images.Media.WIDTH,
//            MediaStore.Images.Media.HEIGHT,
//        )
//        val galleryImage = mutableListOf<GalleryImageData>()
//        val selection =
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) MediaStore.Images.Media.SIZE + " > 0"
//            else null
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
//            resolver?.query(
//                contentUri,
//                projection,
//                Bundle().apply {
//                    // Limit & Offset
//                    putInt(ContentResolver.QUERY_ARG_LIMIT, limit)
//                    putInt(ContentResolver.QUERY_ARG_OFFSET, offset)
//
//                    // Sort function
//                    putStringArray(
//                        ContentResolver.QUERY_ARG_SORT_COLUMNS,
//                        arrayOf(MediaStore.Images.Media.DATE_TAKEN)
//                    )
//                    putInt(
//                        ContentResolver.QUERY_ARG_SORT_DIRECTION,
//                        ContentResolver.QUERY_SORT_DIRECTION_DESCENDING
//                    )
//
//                    // Selection
//                    putString(ContentResolver.QUERY_ARG_SQL_SELECTION, selection)
//                }, null
//            )
//        } else {
//            val sortOrder =
//                "${MediaStore.Images.Media.DATE_TAKEN} DESC LIMIT $limit OFFSET $offset"
//            resolver?.query(
//                contentUri,
//                projection,
//                selection,
//                null,
//                sortOrder
//            )
//        }?.use { cursor ->
//            while (cursor.moveToNext()) {
//                galleryImage.add(
//                    GalleryImageData(
//                        uri = Uri.withAppendedPath(
//                            contentUri,
//                            cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)).toString()
//                        ),
//                        name = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.TITLE)),
//                        fullName = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME)),
//                        mimeType = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.MIME_TYPE)),
//                        addedDate = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATE_TAKEN)),
//                        folder = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME)),
//                        size = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.SIZE)),
//                        width = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.WIDTH)),
//                        height = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.HEIGHT)),
//                    )
//                )
//            }
//        }
//
//        return galleryImage
//    }

    override fun getAllVideos(page: Int): List<GalleryVideoData> {
        val galleryVideoList = mutableListOf<GalleryVideoData>()
        // 외장 메모리에 있는 URI를 받도록 함
        val externalContentUri: Uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI
        // 커서에 가져올 정보에 대해서 지정한다.
        val cursor: Cursor?

        val projection = arrayOf(
            MediaStore.Video.Media._ID,
            MediaStore.Video.Media.TITLE,
            MediaStore.Video.Media.DISPLAY_NAME, // 이름
            MediaStore.Video.Media.MIME_TYPE,
            MediaStore.Video.Media.DATE_TAKEN,
            MediaStore.Video.Media.BUCKET_DISPLAY_NAME,
            MediaStore.Video.Media.SIZE, // 크기
            MediaStore.Video.Media.WIDTH,
            MediaStore.Video.Media.HEIGHT,
        )

        val selection: String? =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) MediaStore.Video.Media.SIZE + " > 0" else null
        val selectionArgs: Array<String>? = null

        cursor = resolver?.query(
            externalContentUri,
            projection,
            selection,
            selectionArgs,
            "${MediaStore.Video.VideoColumns.DATE_ADDED} DESC"
        )

        while (cursor?.moveToNext() == true) {
            galleryVideoList.add(
                GalleryVideoData(
                    title = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.TITLE)),
                    path = ContentUris.withAppendedId(
                        externalContentUri,
                        cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Video.Media._ID))
                    ).toString(),
                    mimeType = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.MIME_TYPE)),
                    duration = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DURATION)),
                    addedDate = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATE_TAKEN)),
                    folder = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.BUCKET_DISPLAY_NAME)),
                    size = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.SIZE)),
                    width = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.WIDTH)),
                    height = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.HEIGHT)),
                )
            )

            cursor.close()
        }

        Log.d("TAG", "getAllVideos: $galleryVideoList")

        return galleryVideoList
    }

    @SuppressLint("Range")
    override fun getMediaFoldersFromMediaStore(
        resolver: ContentResolver
    ): List<GalleryAlbumData> {
        val mediaList = getAllImageVideoFromGallery()
        val recentAlbum = GalleryAlbumData(
            mediaList.firstOrNull()?.mediaData?.toUri() ?: Uri.EMPTY,
            context.getString(R.string.recent_album_name),
            mediaList.size
        )

        return listOf(recentAlbum) + mediaList
            .groupBy { it.albumName }
            .map { (albumName, galleryDataList) ->
                GalleryAlbumData(
                    thumbnail = galleryDataList.firstOrNull()?.mediaData?.toUri() ?: Uri.EMPTY,
                    folderName = albumName,
                    folderFileCount = galleryDataList.size,
                )
            }
    }

    @SuppressLint("Range")
    private fun getAllImageVideoFromGallery(): List<GalleryData> {
        val galleryDataList = mutableListOf<GalleryData>()

        val projection = arrayOf(
            MediaStore.Files.FileColumns._ID,
            MediaStore.Files.FileColumns.DATE_ADDED,
            MediaStore.Files.FileColumns.MEDIA_TYPE,
            MediaStore.Files.FileColumns.DURATION,
            MediaStore.Files.FileColumns.BUCKET_DISPLAY_NAME,
        )

        val selection: String =
            (MediaStore.Files.FileColumns.MEDIA_TYPE + "=" + MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE
                    + " OR " + MediaStore.Files.FileColumns.MEDIA_TYPE + "=" + MediaStore.Files.FileColumns.MEDIA_TYPE_VIDEO)
        val queryUri = MediaStore.Files.getContentUri("external")

        val cursor: Cursor? = resolver?.query(
            queryUri,
            projection,
            selection,
            null,
            MediaStore.Files.FileColumns.DATE_ADDED + " DESC"
        )

        cursor?.let {
            val bucketDisplayNameColumnIndex =
                cursor.getColumnIndex(MediaStore.Files.FileColumns.BUCKET_DISPLAY_NAME)

            while (cursor.moveToNext()) {
                val id = cursor.getLong(cursor.getColumnIndex(MediaStore.Files.FileColumns._ID))
                val mediaType =
                    cursor.getInt(cursor.getColumnIndex(MediaStore.Files.FileColumns.MEDIA_TYPE))
                val folderName = cursor.getString(bucketDisplayNameColumnIndex)

                val galleryData = GalleryData(
                    id = id,
                    mediaType = mediaType,
                    mediaData = if (mediaType == 1) {
                        "content://media/external/images/media/$id"
                    } else {
                        "content://media/external/video/media/$id"
                    },
                    duration = cursor.getInt(cursor.getColumnIndex(MediaStore.Files.FileColumns.DURATION)),
                    albumName = folderName,
                )

                galleryDataList.add(galleryData)
            }
            cursor.close()
        }

        return galleryDataList
    }
}