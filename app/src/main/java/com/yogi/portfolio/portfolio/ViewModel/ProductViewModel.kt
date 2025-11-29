package com.yogi.portfolio.portfolio.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yogi.portfolio.portfolio.Repository.ProductRepository
import com.yogi.portfolio.portfolio.Utils.Resource.ApiResult
import com.yogi.portfolio.portfolio.data.API.RoomEntity.ProductEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(private val repository: ProductRepository) : ViewModel() {

    private var skip = 0
    private val limit = 10

    private val _productState = MutableStateFlow<ApiResult<List<ProductEntity>>>(ApiResult.Loading)
    val productState: StateFlow<ApiResult<List<ProductEntity>>> = _productState

    fun loadProducts() {
        viewModelScope.launch {
            repository.getProducts().collect { result ->
                _productState.value = result
            }
        }
    }

    fun loadMoreProducts() {
        viewModelScope.launch {
            repository.getPagedProducts(limit,skip).collect { result ->
                _productState.value = result
            }
        }
        skip += limit
    }

    fun searchProducrt(query: String) {
        viewModelScope.launch {
            repository.searchProducts(query).collect {
                _productState.value = it
            }
        }
    }
}