package com.example.domain.model

data class UserInfo(
    val id: String?,
    val email: String?,
    val userName: String?,
    val walletAddress: String?,
    val profileImageUrl: String?,
    val balance: Double?,
    val currency: String?
)
