package com.yogi.portfolio.portfolio.domain.model.Request.Responce

data class CartResponse( val carts: List<Cart>,
                         val total: Int,
                         val skip: Int,
                         val limit: Int)
