package com.on.tz.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.on.tz.data.model.Product

class RatingConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromRating(rating: Product.Rating): String {
        return gson.toJson(rating)
    }

    @TypeConverter
    fun toRating(json: String): Product.Rating {
        return gson.fromJson(json, Product.Rating::class.java)
    }
}