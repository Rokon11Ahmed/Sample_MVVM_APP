package com.example.data.repository

import com.example.data.api.LoginApi
import com.example.data.extensions.toUserInfo
import com.example.data.model.LoginRequestModel
import com.example.domain.model.UserInfo
import com.example.domain.repository.LoginRepository
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val api: LoginApi
): LoginRepository{

    override suspend fun getLoginResponse(name: String, pin: String): UserInfo {
        try {
            return api.getLoginResponse(loginRequest = LoginRequestModel(user = name, pin = pin)).toUserInfo()
        }catch (e: Exception){
            return UserInfo(errorMessage = "Something wrong happened")
        }
    }
}