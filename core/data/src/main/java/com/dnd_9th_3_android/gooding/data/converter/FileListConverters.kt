package com.dnd_9th_3_android.gooding.data.converter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.dnd_9th_3_android.gooding.model.feed.model.FeedFile
import com.google.gson.Gson

@ProvidedTypeConverter
class FileListConverters {
    @TypeConverter
    fun listToJson(value: List<FeedFile>) : String? {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun jsonToList(value : String) : List<FeedFile> {
        return Gson().fromJson(value,Array<FeedFile>::class.java).toList()
    }
}