package com.yogi.portfolio.portfolio.Repository

import com.yogi.portfolio.portfolio.data.API.RoomEntity.CartEntity
import kotlinx.coroutines.flow.Flow

interface CartRepository {

    fun getCartItems(): Flow<List<CartEntity>>

    suspend fun addToCart(item: CartEntity)

    suspend fun removeFromCart(productId: Int)

    suspend fun increaseQuantity(productId: Int)

    suspend fun decreaseQuantity(productId: Int)
}