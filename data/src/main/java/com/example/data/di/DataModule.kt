package com.example.data.di

import com.example.data.api.LoginApi
import com.example.data.repository.LoginRepositoryImpl
import com.example.domain.common.Constants.BASE_URL
import com.example.domain.repository.LoginRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun providePaprikaApi(): LoginApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(LoginApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCoinRepository(api: LoginApi): LoginRepository {
        return LoginRepositoryImpl(api)
    }
}