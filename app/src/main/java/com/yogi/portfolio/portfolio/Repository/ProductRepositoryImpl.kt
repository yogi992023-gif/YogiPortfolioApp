package com.yogi.portfolio.portfolio.Repository

import com.yogi.portfolio.portfolio.Utils.Resource.ApiResult
import com.yogi.portfolio.portfolio.data.API.DAO.ProductDao
import com.yogi.portfolio.portfolio.data.API.Mapper.toEntity
import com.yogi.portfolio.portfolio.data.API.ProductApi
import com.yogi.portfolio.portfolio.data.API.RoomEntity.ProductEntity
import com.yogi.portfolio.portfolio.domain.model.Request.Responce.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(private val api: ProductApi,
                                                private val productDao: ProductDao) : ProductRepository {

    override fun getProducts(): Flow<ApiResult<List<ProductEntity>>> = flow {
        emit(ApiResult.Loading)

        try {
            val response = api.getProducts()
            val entities = response.products.map { it.toEntity() }

            productDao.insertProducts(entities)

            emit(ApiResult.Success(entities))
        } catch (e: Exception) {
            emit(ApiResult.Error(e.message ?: "Something went wrong"))
        }
    }

    override fun getProductDetails(id: Int): Flow<ApiResult<ProductEntity>> = flow {
        emit(ApiResult.Loading)

        try {
            val apiResponse = api.getProductDetails(id)
            val entity = apiResponse.toEntity()

            emit(ApiResult.Success(entity))
        } catch (e: Exception) {
            emit(ApiResult.Error(e.message ?: "Failed to fetch product details"))
        }
    }


    override fun getPagedProducts(limit: Int, skip: Int): Flow<ApiResult<List<ProductEntity>>> = flow {
        emit(ApiResult.Loading)
        try {
            val response = api.getProducts(limit, skip)
            val list = response.products.map { it.toEntity() }
            productDao.insertProducts(list)
            emit(ApiResult.Success(list))
        } catch (e: Exception) {
            emit(ApiResult.Error(e.message ?: "Error"))
        }
    }
}