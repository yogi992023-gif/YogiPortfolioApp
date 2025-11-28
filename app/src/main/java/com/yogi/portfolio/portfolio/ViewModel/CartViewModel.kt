package com.yogi.portfolio.portfolio.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.yogi.portfolio.portfolio.Repository.CartRepository
import com.yogi.portfolio.portfolio.data.API.RoomEntity.CartEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class CartViewModel @Inject constructor(private val repository: CartRepository) : ViewModel() {

    val cartItems = repository.getCartItems().asLiveData()

    fun addItem(cart: CartEntity) {
         viewModelScope.launch {
            repository.addToCart(cart)
        }
    }

    fun removeItem(id: Int) {
        viewModelScope.launch {
            repository.removeFromCart(id)
        }
    }

    fun increaseQty(id: Int) {
        viewModelScope.launch {
            repository.increaseQuantity(id)
        }
    }

    fun decreaseQty(id: Int) {
        viewModelScope.launch {
            repository.decreaseQuantity(id)
        }
    }
}