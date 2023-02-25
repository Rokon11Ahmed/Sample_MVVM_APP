package com.example.wind.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.common.Resource
import com.example.domain.login.GetLoginUseCase
import com.example.domain.repository.LoginRepository
import com.example.wind.ui.model.ApiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val getLoginUseCase: GetLoginUseCase,
    private val loginRepository: LoginRepository
) : ViewModel() {

    private val _state = MutableStateFlow(ApiState())
    val state = _state.asStateFlow()

    fun login(name: String, pin: String) {
        loginRepository.getLoginResponse(name = name, pin = pin).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = ApiState(data = result.data)
                }
                is Resource.Error -> {
                    _state.value = ApiState(
                        error = result.message ?: "An unexpected error occurred!"
                    )
                }
                is Resource.Loading -> {
                    _state.value = ApiState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
        /*getLoginUseCase(userName = name, pin = pin).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = ApiState(data = result.data)
                }
                is Resource.Error -> {
                    _state.value = ApiState(
                        error = result.message ?: "An unexpected error occurred!"
                    )
                }
                is Resource.Loading -> {
                    _state.value = ApiState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)*/
    }

    override fun onCleared() {
        super.onCleared()
        _state.value = ApiState()
    }
}