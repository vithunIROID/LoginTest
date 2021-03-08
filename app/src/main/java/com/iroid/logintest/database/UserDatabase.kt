package com.iroid.logintest.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.iroid.logintest.database.dao.UserDAO
import com.iroid.logintest.database.entity.UserEntity

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {

    abstract fun getUserDAO(): UserDAO


    companion object {
        @Volatile private var instance: UserDatabase? = null

        fun getDatabase(context: Context): UserDatabase =
                instance ?: synchronized(this) { instance ?: buildDatabase(context).also { instance = it } }

        private fun buildDatabase(appContext: Context) =
                Room.databaseBuilder(appContext, UserDatabase::class.java, "user_db")
                        .fallbackToDestructiveMigration()
                        .build()
    }

}