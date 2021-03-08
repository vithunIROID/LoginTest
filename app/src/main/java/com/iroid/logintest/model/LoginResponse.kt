package com.iroid.logintest.model

import com.google.gson.annotations.SerializedName

data class LoginResponse(

        val errorCode: String,
        val errorMessage: String,
        val user: UserData
    )