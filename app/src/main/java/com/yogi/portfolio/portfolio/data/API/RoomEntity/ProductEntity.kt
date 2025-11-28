package com.yogi.portfolio.portfolio.data.API.RoomEntity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class ProductEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val description: String,
    val price: Double,
    val image: String,
    val brand: String?,
    val category: String
)
