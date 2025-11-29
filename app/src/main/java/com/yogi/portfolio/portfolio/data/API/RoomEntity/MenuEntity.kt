package com.yogi.portfolio.portfolio.data.API.RoomEntity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "menu_table")
data class MenuEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val iconRes: Int
)