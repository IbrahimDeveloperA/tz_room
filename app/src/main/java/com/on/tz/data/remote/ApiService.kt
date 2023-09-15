package com.on.tz.data.remote

import com.on.tz.data.model.Category
import com.on.tz.data.model.Product
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("products/categories")
    suspend fun getCategories(): List<String>

    @GET("products/category/{categoryId}")
    suspend fun getProductsByCategory(
        @Path("categoryId") category: String,
        @Query("sort") sort: String? = null
    ): List<Product>

    @GET("products/{id}")
    suspend fun getProducts(
        @Path("id") id: Int?
    ): Product

}