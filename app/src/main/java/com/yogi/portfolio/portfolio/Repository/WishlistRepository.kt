package com.yogi.portfolio.portfolio.Repository

import com.yogi.portfolio.portfolio.data.API.DAO.WishlistDao
import com.yogi.portfolio.portfolio.data.API.RoomEntity.WishlistEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WishlistRepository @Inject constructor(
    private val dao: WishlistDao
) {

    fun getWishlist() = dao.getWishlist()

    suspend fun add(item: WishlistEntity) {
        dao.addToWishlist(item)
    }

    suspend fun remove(item: WishlistEntity) {
        dao.removeFromWishlist(item)
    }

    suspend fun clear() {
        dao.clearWishlist()
    }
}
