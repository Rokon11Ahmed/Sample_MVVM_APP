package com.example.domain.repository

import com.example.domain.common.Resource
import com.example.domain.model.UserInfo
import kotlinx.coroutines.flow.Flow

interface LoginRepository {
    fun getLoginResponse(name: String, pin: String): Flow<Resource<UserInfo>>
}