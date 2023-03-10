package com.example.domain.model

data class UserInfo(
    val id: String? = null,
    val email: String? = null,
    val userName: String? = null,
    val walletAddress: String? = null,
    val profileImageUrl: String? = null,
    val balance: Double? = null,
    val currency: String? = null,
    val errorMessage: String? = null
)
