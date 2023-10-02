package com.dnd_9th_3_android.gooding.data.converter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.dnd_9th_3_android.gooding.model.feed.model.UserInfo
import com.google.gson.Gson

@ProvidedTypeConverter
class UserStringConverters {
    @TypeConverter
    fun userToJson(value : UserInfo) : String? {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun jsonToUser(value : String) : UserInfo{
        return Gson().fromJson(value,UserInfo::class.java)
    }
}