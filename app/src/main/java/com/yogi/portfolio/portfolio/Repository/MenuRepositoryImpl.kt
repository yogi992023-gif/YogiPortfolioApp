package com.yogi.portfolio.portfolio.Repository

import androidx.lifecycle.LiveData
import com.yogi.portfolio.portfolio.data.API.DAO.MenuDao
import com.yogi.portfolio.portfolio.data.API.RoomEntity.MenuEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class MenuRepositoryImpl @Inject constructor(private val menuDao: MenuDao) : MenuRepository {

    override suspend fun insertMenu(item: MenuEntity) {
        /*if(menuDao.getMenuCount() == 0){
            menuDao.insertMenu(item)
        }*/
        menuDao.insertMenu(item)

    }

    override fun getMenuItems(): LiveData<List<MenuEntity>> {
        return menuDao.getAllMenus()
    }
}