package com.dnd_9th_3_android.gooding.data.converter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.dnd_9th_3_android.gooding.model.feed.model.FeedData
import com.google.gson.Gson

@ProvidedTypeConverter
class FileListConverters {
    @TypeConverter
    fun listToJson(value: List<FeedData>) : String? {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun jsonToList(value : String) : List<FeedData> {
        return Gson().fromJson(value,Array<FeedData>::class.java).toList()
    }
}