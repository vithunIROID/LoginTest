package com.iroid.logintest.network

import com.iroid.logintest.model.LoginResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Field

interface ApiHelper {

    suspend fun login(email: String, password: String): Response<LoginResponse>
}