package com.example.domain.login

import android.util.Log
import com.example.domain.common.Resource
import com.example.domain.model.UserInfo
import com.example.domain.repository.LoginRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

class GetLoginUseCase @Inject constructor(
    private val repository: LoginRepository
) {
    operator fun invoke(userName: String, pin: String): Flow<Resource<UserInfo>> = flow {
        emit(Resource.Loading())
        try {
            Log.d("TAG", "invoke called: ")

            repository.getLoginResponse(name = userName, pin = pin)
                .onEach {dataState ->
                    emit(dataState)
                }.flowOn(Dispatchers.Main)
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection!"))
        }
    }

}