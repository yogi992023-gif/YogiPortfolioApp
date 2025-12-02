package com.yogi.portfolio.portfolio.data.API.DAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yogi.portfolio.portfolio.data.API.RoomEntity.MenuEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MenuDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMenu(menu: MenuEntity)

    @Query("SELECT * FROM menu_table")
    fun getAllMenus(): LiveData<List<MenuEntity>>

    @Query("SELECT COUNT(*) FROM menu_table")
    suspend fun getMenuCount(): Int
}