package com.example.domain.login

import com.example.domain.common.Resource
import com.example.domain.model.UserInfo
import com.example.domain.repository.LoginRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetLoginUseCase @Inject constructor(
    private val repository: LoginRepository
) {
    operator fun invoke(userName: String, pin: String): Flow<Resource<UserInfo>> =
        repository.getLoginResponse(name = userName, pin = pin)
            .map {
                return@map it
            }.flowOn(Dispatchers.IO)

}