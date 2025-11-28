package com.yogi.portfolio.portfolio.data.API.DB

import androidx.room.Database
import androidx.room.RoomDatabase
import com.yogi.portfolio.portfolio.data.API.DAO.CartDao
import com.yogi.portfolio.portfolio.data.API.DAO.ProductDao
import com.yogi.portfolio.portfolio.data.API.RoomEntity.CartEntity
import com.yogi.portfolio.portfolio.data.API.RoomEntity.ProductEntity

@Database(entities = [ProductEntity::class, CartEntity::class],
    version = 5,
    exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDao
    abstract fun cartDao(): CartDao
}