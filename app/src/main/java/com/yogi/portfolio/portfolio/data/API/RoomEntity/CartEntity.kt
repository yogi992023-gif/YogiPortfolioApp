package com.yogi.portfolio.portfolio.data.API.RoomEntity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart")
data class CartEntity(
    @PrimaryKey val productId: Int,
    val title: String,
    val price: Double,
    val image: String,
    var quantity: Int = 1
)
