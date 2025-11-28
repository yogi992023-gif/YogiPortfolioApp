package com.yogi.portfolio.portfolio.data.API.Mapper

import com.yogi.portfolio.portfolio.data.API.RoomEntity.ProductEntity
import com.yogi.portfolio.portfolio.domain.model.Request.Responce.Product

fun Product.toEntity(): ProductEntity {
    return ProductEntity(
        id = id,
        title = title,
        description = description,
        price = price,
        image = thumbnail,
        brand = brand ?: "N?A",
        category = category
    )
}