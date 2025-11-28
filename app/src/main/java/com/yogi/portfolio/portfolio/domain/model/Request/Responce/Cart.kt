package com.yogi.portfolio.portfolio.domain.model.Request.Responce

data class Cart(val id: Int,
                val products: List<CartProduct>,
                val total: Int,
                val discountedTotal: Int,
                val userId: Int,
                val totalProducts: Int,
                val totalQuantity: Int)
