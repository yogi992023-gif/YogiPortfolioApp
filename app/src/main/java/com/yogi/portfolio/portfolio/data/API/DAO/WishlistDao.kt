package com.yogi.portfolio.portfolio.data.API.DAO

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yogi.portfolio.portfolio.data.API.RoomEntity.WishlistEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WishlistDao {

    @Query("SELECT * FROM wishlist")
    fun getWishlist(): Flow<List<WishlistEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToWishlist(item: WishlistEntity)

    @Delete
    suspend fun removeFromWishlist(item: WishlistEntity)

    @Query("DELETE FROM wishlist")
    suspend fun clearWishlist()


}
