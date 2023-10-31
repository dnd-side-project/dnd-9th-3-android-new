package com.dnd_9th_3_android.gooding.data.dataRecord.repository

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.content.ContentUris
import android.content.Context
import android.database.Cursor
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import androidx.core.os.bundleOf
import com.dnd_9th_3_android.gooding.data.dataRecord.domain.ImageRepository
import com.dnd_9th_3_android.gooding.model.record.GalleryImage
import com.dnd_9th_3_android.gooding.model.record.ImageFolder
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.File
import javax.inject.Inject

class ImageRepositoryImpl @Inject constructor(
    @ApplicationContext private val context : Context
) : ImageRepository{

    // 버전 대응 -> API 30 이상이므로 필요 없음
    private val uriExternal : Uri by lazy {
        MediaStore.Images.Media.getContentUri(
            MediaStore.VOLUME_EXTERNAL
        )
    }

    // 데이터 형식
    private val projection = arrayOf(
        MediaStore.Images.ImageColumns.DATA,
        MediaStore.Images.ImageColumns.DISPLAY_NAME,
        MediaStore.Images.ImageColumns.DATE_TAKEN,
        MediaStore.Images.ImageColumns._ID
    )

    // 이미지 정렬 기준
    private val sortedOrder = MediaStore.Images.ImageColumns.DATE_TAKEN
    // content resolver 활용
    private val contentResolver by lazy { context.contentResolver }

    override fun getAllPhotos(
        page: Int,
        loadSize: Int,
        currentLocation: String?
    ): MutableList<GalleryImage> {
        val galleryImageList = mutableListOf<GalleryImage>()
        var selection : String? = null
        var selectionArgs : Array<String>? = null

        if (currentLocation != null){
            selection = "${MediaStore.Images.Media.DATA} LIKE ?"
            selectionArgs = arrayOf("%$currentLocation%")
        }

        val offset = (page - 1) * loadSize // 오프셋 위치
        val query = getQuery(offset, loadSize, selection, selectionArgs)

        query?.use { cursor ->
            while (cursor.moveToNext()){
                val id =
                    cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Images.ImageColumns._ID))
                val name =
                    cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.ImageColumns.DISPLAY_NAME))
                val filePath =
                    cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.ImageColumns.DATA))
                val date =
                    cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.ImageColumns.DATE_TAKEN))
                val contentUri = ContentUris.withAppendedId(uriExternal, id)
                val image = GalleryImage(
                    id = id,
                    filePath = filePath,
                    uri = contentUri,
                    name = name,
                    date = date ?: "",
                    size = 0,
                )
                galleryImageList.add(image)
            }
        }
        return galleryImageList
    }

    override fun getFolderList(): ArrayList<ImageFolder> {
        var totalCount = 0
        val imageFolderList = ArrayList<ImageFolder>()
        val folderList = ArrayList<String>()
        val uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val projection = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = context.contentResolver.query(uri, projection, null, null, null)
        if (cursor!=null){
            while (cursor.moveToNext()){
                val columnIndex = cursor.getColumnIndex(MediaStore.Images.Media.DATA)
                val filePath = cursor.getString(columnIndex)
                val folder = File(filePath).parent
                val imageFolder = makeImageFolder(folder)
                if (!folderList.contains(folder)) {
                    totalCount += imageFolder.imageCount
                    imageFolderList.add(imageFolder)
                    folderList.add(folder)
                }
            }
            cursor.close()
        }
        imageFolderList.add(0,ImageFolder("최근 항목",totalCount,getFirstImage(),""))
        return imageFolderList
    }

    @SuppressLint("Recycle")
    override fun getFirstImage(): String? {
        val projection = arrayOf(
            MediaStore.Images.ImageColumns._ID,
            MediaStore.Images.ImageColumns.DATA,
            MediaStore.Images.ImageColumns.BUCKET_DISPLAY_NAME,
            MediaStore.Images.ImageColumns.DATE_TAKEN,
            MediaStore.Images.ImageColumns.MIME_TYPE
        )
        val cursor = context.contentResolver
            .query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, projection, null, null,
                MediaStore.Images.ImageColumns.DATE_TAKEN + " DESC")

        return if (cursor!!.moveToFirst()) {
            cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.ImageColumns.DATA))
        }else{
            null
        }
    }

    override fun makeImageFolder(folder: String): ImageFolder {
        val name = folder.split("/").last()
        var imageCount = 0
        var firstImage : String? = null
        val file = File(folder)
        val imageList = file.listFiles()
        if (imageList != null) {
            imageCount = imageList.size
            firstImage = imageList[0]?.absolutePath
        }


        return ImageFolder(name,imageCount,firstImage,folder)
    }

    @SuppressLint("Recycle")
    private fun getQuery(
        offset: Int,
        limit: Int,
        selection: String?,
        selectionArgs: Array<String>?,
    ) : Cursor?{
        val bundle = bundleOf(
            ContentResolver.QUERY_ARG_OFFSET to offset,
            ContentResolver.QUERY_ARG_LIMIT to limit,
            ContentResolver.QUERY_ARG_SORT_COLUMNS to arrayOf(MediaStore.Files.FileColumns.DATE_MODIFIED),
            ContentResolver.QUERY_ARG_SORT_DIRECTION to ContentResolver.QUERY_SORT_DIRECTION_DESCENDING,
            ContentResolver.QUERY_ARG_SQL_SELECTION to selection,
            ContentResolver.QUERY_ARG_SQL_SELECTION_ARGS to selectionArgs,
        )
        return contentResolver.query(uriExternal, projection, bundle, null)
    }
}