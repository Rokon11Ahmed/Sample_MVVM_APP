package com.example.wind.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.common.Resource
import com.example.domain.login.GetLoginUseCase
import com.example.wind.ui.model.ApiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val getLoginUseCase: GetLoginUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(ApiState())
    val state = _state.asStateFlow()

    fun login(name: String, pin: String) {
        getLoginUseCase(userName = name, pin = pin)
            .onEach { result ->
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
            }
            .catch { println(it) }
            .launchIn(viewModelScope)
    }

    override fun onCleared() {
        super.onCleared()
        _state.value = ApiState()
    }
}