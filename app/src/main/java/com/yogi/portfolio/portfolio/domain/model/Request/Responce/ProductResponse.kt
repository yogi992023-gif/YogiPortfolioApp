package com.yogi.portfolio.portfolio.domain.model.Request.Responce

data class ProductResponse(val products: List<Product>,
                           val total: Int,
                           val skip: Int,
                           val limit: Int)
