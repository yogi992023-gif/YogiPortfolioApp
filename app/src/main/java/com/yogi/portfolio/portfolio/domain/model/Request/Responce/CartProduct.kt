package com.yogi.portfolio.portfolio.domain.model.Request.Responce

data class CartProduct(val id: Int,
                       val title: String,
                       val price: Int,
                       val quantity: Int,
                       val total: Int,
                       val discountPercentage: Double,
                       val discountedPrice: Int)
