package com.yogi.portfolio.portfolio.data.API.RoomEntity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wishlist")
data class WishlistEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val price: Double,
    val image: String
)