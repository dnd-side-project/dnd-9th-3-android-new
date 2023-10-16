package com.dnd_9th_3_android.gooding.data.converter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.dnd_9th_3_android.gooding.model.feed.model.UserData
import com.google.gson.Gson

@ProvidedTypeConverter
class UserStringConverters {
    @TypeConverter
    fun userToJson(value : UserData) : String? {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun jsonToUser(value : String) : UserData{
        return Gson().fromJson(value,UserData::class.java)
    }
}