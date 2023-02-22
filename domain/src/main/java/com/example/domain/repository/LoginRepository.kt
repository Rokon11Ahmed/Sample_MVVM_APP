package com.example.domain.repository

import com.example.domain.model.UserInfo

interface LoginRepository {
    suspend fun getLoginResponse(name: String, pin: String): UserInfo
}