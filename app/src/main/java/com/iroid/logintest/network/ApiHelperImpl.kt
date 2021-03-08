package com.iroid.logintest.network

import com.iroid.logintest.model.LoginResponse
import com.iroid.logintest.network.ApiService
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: ApiService) : ApiHelper {

    override suspend fun login(email: String, password: String): Response<LoginResponse> = apiService.login(email, password)

}