package com.example.wind.ui.model

import com.example.domain.model.UserInfo

data class ApiState(
        val isLoading: Boolean = false,
        val data: Any? = null,
        val error: String = ""
)
