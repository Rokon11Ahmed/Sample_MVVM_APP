package com.example.data.api

import com.example.data.model.LoginRequestModel
import com.example.data.model.LoginResponseModel
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface LoginApi {

    @Headers("Accept: application/json")
    @POST("v1/login")
    suspend fun getLoginResponse(@Body loginRequest: LoginRequestModel): ApiResponse<LoginResponseModel>
}