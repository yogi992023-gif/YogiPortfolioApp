package com.yogi.portfolio.portfolio.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yogi.portfolio.portfolio.data.auth.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AuthViewModel @Inject constructor(private val repo: AuthRepository) : ViewModel() {

    private val _authState = MutableLiveData<Result<Unit>>()
    val authState: LiveData<Result<Unit>> = _authState

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _authState.value = repo.login(email, password)
        }
    }

    fun register(email: String, password: String) {
        viewModelScope.launch {
            _authState.value = repo.register(email, password)
        }
    }

    fun logout() {
        repo.logout()
    }

    fun isLoggedIn(): Boolean = repo.isUserLoggedIn()
}