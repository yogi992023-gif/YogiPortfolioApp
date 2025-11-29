package com.yogi.portfolio.portfolio.Repository

import androidx.lifecycle.LiveData
import com.yogi.portfolio.portfolio.data.API.RoomEntity.MenuEntity
import kotlinx.coroutines.flow.Flow

interface MenuRepository {

  //  val menuList = dao.getAllMenus()
    suspend fun insertMenu(item : MenuEntity)

    fun getMenuItems(): LiveData<List<MenuEntity>>

}