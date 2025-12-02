package com.yogi.portfolio.portfolio.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yogi.portfolio.portfolio.Repository.MenuRepository
import com.yogi.portfolio.portfolio.data.API.RoomEntity.MenuEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MenuViewModel@Inject constructor(private val repo: MenuRepository) : ViewModel() {

    val menus: LiveData<List<MenuEntity>> = repo.getMenuItems()

    fun addMenu(title: String, iconRes: Int) {
        viewModelScope.launch {
            repo.insertMenu(MenuEntity(menuName = title, menuIcon = iconRes))
        }
    }
}