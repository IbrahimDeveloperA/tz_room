package com.on.tz.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson

class Converters {
    @TypeConverter
    fun fromString(value: String?): List<String>? {
        return value?.let { Gson().fromJson(it, List::class.java) as List<String> }
    }

    @TypeConverter
    fun toString(value: List<String>?): String? {
        return Gson().toJson(value)
    }
}