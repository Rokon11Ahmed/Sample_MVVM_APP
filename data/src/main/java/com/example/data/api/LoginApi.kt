package com.example.data.api

import com.example.data.model.LoginRequestModel
import com.example.data.model.LoginResponseModel
import retrofit2.http.Body
import retrofit2.http.GET

interface LoginApi {

    @GET("/v1/login")
    suspend fun getLoginResponse(@Body loginRequest: LoginRequestModel): LoginResponseModel
}