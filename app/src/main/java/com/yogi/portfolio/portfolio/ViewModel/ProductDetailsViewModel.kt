package com.yogi.portfolio.portfolio.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yogi.portfolio.portfolio.data.API.DAO.ProductDao
import com.yogi.portfolio.portfolio.data.API.RoomEntity.ProductEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    private val productDao: ProductDao) : ViewModel() {

    val isLoading = MutableLiveData<Boolean>()

    private val _product = MutableStateFlow<ProductEntity?>(null)
    val product = _product.asStateFlow()

    fun getProduct(id: Int) {
        isLoading.value = true
        viewModelScope.launch {
            try{
                _product.value = productDao.getProductById(id)
            }catch (e : Exception){
                e.printStackTrace()
                isLoading.value = false
            }finally {
                isLoading.value = false
            }

        }
    }
}