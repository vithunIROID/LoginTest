package com.iroid.logintest.repository

import androidx.lifecycle.LiveData
import com.iroid.logintest.database.UserDatabase
import com.iroid.logintest.database.dao.UserDAO
import com.iroid.logintest.database.entity.UserEntity
import com.iroid.logintest.network.ApiHelper
import dagger.Module
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


class UserRepository @Inject constructor(private val apiHelper: ApiHelper, private val userDatabase: UserDatabase) {

    suspend fun userLogin(email: String, password: String) =  apiHelper.login(email, password)

    suspend fun insertUser(userEntity: UserEntity) {
        userDatabase.getUserDAO().addUser(userEntity)
   }

}