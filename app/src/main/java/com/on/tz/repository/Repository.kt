package com.on.tz.repository

import com.on.tz.core.base.BaseRepository
import com.on.tz.data.local.ProductDao
import com.on.tz.data.model.Category
import com.on.tz.data.model.CategoryEntity
import com.on.tz.data.model.Product
import com.on.tz.data.remote.ApiService
import com.on.tz.utils.mapCategoriesFromApiToEntity
import kotlinx.coroutines.internal.synchronized
import javax.inject.Inject

class Repository @Inject constructor(
    private val apiService: ApiService,
    private val productDao: ProductDao,
) : BaseRepository() {

    fun getCategory(category: String, sort: String?) = doRequest {
        productDao.insertCategory(apiService.getProductsByCategory(category, sort))
        productDao.getProductFromRoom()
    }

    fun getCategories() = doRequest {
//        productDao.insertCategories(apiService.getCategories() as List<`CategoryEntity`>)
//        productDao.getCategories()
        val apiCategories = apiService.getCategories()
        val categoryEntities = mapCategoriesFromApiToEntity(apiCategories)
        productDao.insertCategories(categoryEntities)
        productDao.getCategories()
    }

    fun getProducts(id: Int?) = doRequest {
        apiService.getProducts(id)
    }

}