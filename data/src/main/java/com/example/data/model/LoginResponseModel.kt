package com.example.data.model

import com.google.gson.annotations.SerializedName

data class LoginResponseModel(
    val status: Boolean,
    val data: Data? = null,
    val messages: ArrayList<String>? = null
)

data class Data(
    val userInfo: UserInfo?,
    val accountInfo: AccountInfo?
)

data class UserInfo(
    val Id: Int,
    @SerializedName("Email")
    val email: String,
    @SerializedName("UserName")
    val userName: String,
    @SerializedName("WalletAddress")
    val walletAddress: String,
    val smartContactWallet: String,
    @SerializedName("ProfileImage")
    val profileImage: String
)

data class AccountInfo(
    val balance: Double,
    val currency: String
)
