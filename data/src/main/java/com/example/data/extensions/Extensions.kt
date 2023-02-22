package com.example.data.extensions

import com.example.data.model.LoginResponseModel
import com.example.domain.model.UserInfo

fun LoginResponseModel.toUserInfo(): UserInfo {
    return UserInfo(
        data?.userInfo?.Id.toString(),
        data?.userInfo?.email,
        data?.userInfo?.userName,
        data?.userInfo?.profileImage,
        data?.accountInfo?.balance?.toDouble(),
        data?.accountInfo?.currency
    )
}