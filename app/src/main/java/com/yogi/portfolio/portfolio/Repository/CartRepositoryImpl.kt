package com.yogi.portfolio.portfolio.Repository

import com.yogi.portfolio.portfolio.data.API.DAO.CartDao
import com.yogi.portfolio.portfolio.data.API.RoomEntity.CartEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(private val cartDao: CartDao) : CartRepository {

    override fun getCartItems(): Flow<List<CartEntity>>  {
         return cartDao.getCartItems()
    }

    override suspend fun addToCart(item: CartEntity) {
        cartDao.insertCartItem(item)
    }

    override suspend fun removeFromCart(productId: Int) {
        cartDao.removeCartItem(productId)
    }

    override suspend fun increaseQuantity(productId: Int) {
        cartDao.increaseQuantity(productId)
    }

    override suspend fun decreaseQuantity(productId: Int) {
        cartDao.decreaseQuantity(productId)
    }

    suspend fun plus(id: Int) = cartDao.increaseQuantity(id)
    suspend fun minus(id: Int) = cartDao.decreaseQuantity(id)
}