package com.yogi.portfolio.portfolio.Repository

import com.yogi.portfolio.portfolio.Utils.Resource.ApiResult
import com.yogi.portfolio.portfolio.data.API.RoomEntity.ProductEntity
import kotlinx.coroutines.flow.Flow

interface ProductRepository {

    fun getProducts() : Flow<ApiResult<List<ProductEntity>>>
    fun getProductDetails(id : Int) : Flow<ApiResult<ProductEntity>>

    fun getPagedProducts(limit : Int, skip : Int) : Flow<ApiResult<List<ProductEntity>>>
}