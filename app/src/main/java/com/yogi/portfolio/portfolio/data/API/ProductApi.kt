package com.yogi.portfolio.portfolio.data.API

import com.yogi.portfolio.portfolio.domain.model.Request.Responce.Product
import com.yogi.portfolio.portfolio.domain.model.Request.Responce.ProductResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductApi {

    @GET("products")
    suspend fun getProducts() : ProductResponse

    @GET("products/{id}")
    suspend fun getProductDetails(@Path("id")id: Int) : Product

    @GET("products/search")
    suspend fun searchProducts(@Query("q")query: String) : ProductResponse

    @GET("products")
    suspend fun getProducts(@Query("limit") limit: Int, @Query("skip") skip: Int): ProductResponse

}