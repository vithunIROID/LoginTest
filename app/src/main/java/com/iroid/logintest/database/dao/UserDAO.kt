package com.iroid.logintest.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.iroid.logintest.database.entity.UserEntity

@Dao
interface UserDAO {
    @Insert
    suspend fun addUser(userEntity: UserEntity)

    @Query("SELECT * FROM user")
    fun getAllUsers() : LiveData<List<UserEntity>>

    @Query("SELECT * FROM user WHERE user_id = :userID")
    fun getUser(userID: Long) : UserEntity?
}