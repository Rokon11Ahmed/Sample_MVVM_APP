package com.example.data.api

import com.example.data.model.LoginRequestModel
import com.example.data.model.LoginResponseModel
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface LoginApi {

    @POST("v1/login")
    suspend fun getLoginResponse(@Body loginRequest: LoginRequestModel): LoginResponseModel
}