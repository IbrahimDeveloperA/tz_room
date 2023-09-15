package com.on.tz.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "categories")
data class CategoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = 0, // You should use a suitable primary key for categories
    val name: String
)

@Entity(tableName = "product_list")
data class Product(
    val category: String,
    val description: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val image: String,
    val price: Double,
    val rating: Rating,
    val title: String
) : Serializable {
    data class Rating(
        val count: Int,
        val rate: Double
    ) : Serializable
}