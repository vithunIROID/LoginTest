package com.iroid.logintest.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntity(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "_id")
        val _id: Long,
        @ColumnInfo(name = "user_id")
        val user_id: String,
        @ColumnInfo(name = "user_name")
        val user_name: String
    ){
    constructor(user_id: String, user_name: String) : this(0, user_id, user_name)
}