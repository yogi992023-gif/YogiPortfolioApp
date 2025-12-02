package com.yogi.portfolio.portfolio.data.API.DB

import androidx.room.Database
import androidx.room.RoomDatabase
import com.yogi.portfolio.portfolio.data.API.DAO.CartDao
import com.yogi.portfolio.portfolio.data.API.DAO.MenuDao
import com.yogi.portfolio.portfolio.data.API.DAO.ProductDao
import com.yogi.portfolio.portfolio.data.API.RoomEntity.CartEntity
import com.yogi.portfolio.portfolio.data.API.RoomEntity.MenuEntity
import com.yogi.portfolio.portfolio.data.API.RoomEntity.ProductEntity

@Database(entities = [ProductEntity::class, CartEntity::class, MenuEntity::class],
    version = 7,
    exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao() : ProductDao
    abstract fun cartDao() : CartDao
    abstract fun menuDao() : MenuDao
}