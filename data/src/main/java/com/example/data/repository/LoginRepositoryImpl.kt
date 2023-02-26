package com.example.data.repository

import android.util.Log
import com.example.data.api.LoginApi
import com.example.data.extensions.toUserInfo
import com.example.data.model.LoginRequestModel
import com.example.domain.common.Resource
import com.example.domain.model.UserInfo
import com.example.domain.repository.LoginRepository
import com.skydoves.sandwich.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val api: LoginApi
) : LoginRepository {

    override fun getLoginResponse(name: String, pin: String): Flow<Resource<UserInfo>> = flow {
        Log.d("TAG", "invoke API called: ")
        emit(Resource.Loading())
        val response = api.getLoginResponse(
            loginRequest = LoginRequestModel(
                user = name,
                pin = pin
            )
        )
        response.suspendOnSuccess {
            emit(Resource.Success(data.toUserInfo()))
        }
        response.suspendOnError {
            emit(Resource.Error(message()))
        }
        response.suspendOnException {
            emit(Resource.Error(exception.message))
        }
    }
}