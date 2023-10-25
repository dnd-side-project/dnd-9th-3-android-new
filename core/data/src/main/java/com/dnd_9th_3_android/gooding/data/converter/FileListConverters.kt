package com.dnd_9th_3_android.gooding.data.converter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.dnd_9th_3_android.gooding.model.file.FileData
import com.google.gson.Gson

@ProvidedTypeConverter
class FileListConverters {
    @TypeConverter
    fun listToJson(value: List<FileData>) : String? {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun jsonToList(value : String) : List<FileData> {
        return Gson().fromJson(value,Array<FileData>::class.java).toList()
    }
}