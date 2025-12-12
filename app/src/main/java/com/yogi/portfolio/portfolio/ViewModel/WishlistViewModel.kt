package com.yogi.portfolio.portfolio.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yogi.portfolio.portfolio.Repository.WishlistRepository
import com.yogi.portfolio.portfolio.data.API.RoomEntity.ProductEntity
import com.yogi.portfolio.portfolio.data.API.RoomEntity.WishlistEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@HiltViewModel
class WishlistViewModel @Inject constructor(
    private val repo: WishlistRepository
) : ViewModel() {

    val wishlist = repo.getWishlist()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    fun toggleWishlist(product: ProductEntity) {
        val item = WishlistEntity(
            id = product.id,
            title = product.title,
            price = product.price,
            image = product.image
        )

        viewModelScope.launch {
            if (wishlist.value.any { it.id == item.id }) {
                repo.remove(item)
            } else {
                repo.add(item)
            }
        }
    }
}
