package com.example.wind.ui.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.common.Resource
import com.example.domain.login.GetLoginUseCase
import com.example.wind.ui.model.ApiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val getLoginUseCase: GetLoginUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(ApiState())
    val state = _state.asStateFlow()

    fun login (name: String, pin: String){
        getLoginUseCase(userName = name, pin = pin).onEach { result ->
            when (result){
                is Resource.Success -> {
                    _state.value = ApiState(data = result.data)
                }
                is Resource.Error -> {
                    _state.value = ApiState(error = result.message ?: "An unexpected error occurred!")
                }
                is Resource.Loading -> {
                    _state.value = ApiState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}