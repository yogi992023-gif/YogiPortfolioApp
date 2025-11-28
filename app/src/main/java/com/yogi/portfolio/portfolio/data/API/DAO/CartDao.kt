package com.yogi.portfolio.portfolio.data.API.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yogi.portfolio.portfolio.data.API.RoomEntity.CartEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCartItem(item: CartEntity)

    @Query("SELECT * FROM cart")
    fun getCartItems(): Flow<List<CartEntity>>

    @Query("DELETE FROM cart WHERE productId = :productId")
    suspend fun removeCartItem(productId: Int)

    @Query("UPDATE cart SET quantity = quantity + 1 WHERE productId = :id")
    suspend fun increaseQuantity(id: Int)

    @Query("UPDATE cart SET quantity = quantity - 1 WHERE productId = :id AND quantity > 1")
    suspend fun decreaseQuantity(id: Int)
}