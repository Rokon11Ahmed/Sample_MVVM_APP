package com.example.wind.ui.model

data class ApiState(
    val isLoading: Boolean = false,
    val data: Any? = null,
    val error: String = ""
)
