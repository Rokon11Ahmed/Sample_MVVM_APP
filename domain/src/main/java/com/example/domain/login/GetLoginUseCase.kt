package com.example.domain.login

import android.util.Log
import com.example.domain.common.Resource
import com.example.domain.model.UserInfo
import com.example.domain.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetLoginUseCase @Inject constructor(
    private val repository: LoginRepository
){
    operator fun invoke(userName: String, pin: String): Flow<Resource<UserInfo>> = flow {
        try {
            emit(Resource.Loading())
            val coins = repository.getLoginResponse(name = userName, pin = pin)
            emit(Resource.Success(coins))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection!"))
        }
    }
}