package com.on.tz.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.on.tz.data.model.Category
import com.on.tz.data.model.CategoryEntity
import com.on.tz.data.model.Product

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategories(categories: List<CategoryEntity>)

    @Query("SELECT * FROM categories")
    suspend fun getCategories(): List<CategoryEntity>


//    @Insert
//    suspend fun saveCategories(categories: List<Category>): List<Category>

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun saveCategories(categories: List<String>)

    //
//    @Query("SELECT * FROM product_list WHERE category = :category")
//    suspend fun getProductsByCategory(category: String): List<Product>

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertProducts(products: List<Product>)
//    //

//    @Query("SELECT * FROM product_list")
//    suspend fun getAll(): List<String>

    @Query("SELECT * from product_list ")
    suspend fun getProductFromRoom(): List<Product>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(categories: List<Product>)


//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertCarsToRoom(cars:List<Product>)
//
//    @Insert
//    suspend fun addData(product: Product)


}