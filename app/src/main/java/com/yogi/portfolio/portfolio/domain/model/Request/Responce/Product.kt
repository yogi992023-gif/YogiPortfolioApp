package com.yogi.portfolio.portfolio.domain.model.Request.Responce

import com.yogi.portfolio.portfolio.data.API.RoomEntity.ProductEntity

data class Product(val id: Int,
                   val title: String,
                   val description: String,
                   val price: Double,
                   val discountPercentage: Double,
                   val rating: Double,
                   val stock: Int,
                   val brand: String,
                   val category: String,
                   val thumbnail: String,
                   val images: List<String>){

}
