package com.yogi.portfolio.portfolio.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yogi.portfolio.portfolio.Repository.CartRepository
import com.yogi.portfolio.portfolio.Repository.WishlistRepository
import com.yogi.portfolio.portfolio.data.API.RoomEntity.CartEntity
import com.yogi.portfolio.portfolio.data.API.RoomEntity.ProductEntity
import com.yogi.portfolio.portfolio.data.API.RoomEntity.WishlistEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WishlistBadgeViewModel @Inject constructor(val repository: WishlistRepository, val repoCart : CartRepository) : ViewModel() {

    val wishlistCount = repository.getWishlist()
        .map { it.size }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000),
            0
        )

    fun toggleCartlist(wishProduct: WishlistEntity) {
        val item = CartEntity(
            productId = wishProduct.id,
            title = wishProduct.title,
            price = wishProduct.price,
            image = wishProduct.image
        )

        viewModelScope.launch {
            /*if (wishlistCount.value.any { it.id == item.productId }) {
                repoCart.removeFromCart(item)
            } else {*/
                repoCart.addToCart(item)
         //   }
        }
    }
}