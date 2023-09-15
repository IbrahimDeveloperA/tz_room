package com.on.tz.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.on.tz.data.model.Category
import com.on.tz.data.model.CategoryEntity
import com.on.tz.data.model.Product

@Database(entities = [CategoryEntity::class, Product::class], version = 2)
@TypeConverters(RatingConverter::class,Converters::class)
abstract class ProductDatabase :RoomDatabase() {
    abstract fun productDao():ProductDao
}