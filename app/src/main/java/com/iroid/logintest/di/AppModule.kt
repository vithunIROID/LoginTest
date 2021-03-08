package com.iroid.logintest.di

import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.iroid.logintest.database.UserDatabase
import com.iroid.logintest.network.ApiHelper
import com.iroid.logintest.network.ApiHelperImpl
import com.iroid.logintest.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule{

//    @Provides
//    fun provideOkHttpClient() = {
//        val loggingInterceptor = HttpLoggingInterceptor()
//        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
//        OkHttpClient.Builder()
//            .addInterceptor(loggingInterceptor)
//            .connectionSpecs(
//                Arrays.asList(
//                    ConnectionSpec.CLEARTEXT,
//                    ConnectionSpec.MODERN_TLS,
//                    ConnectionSpec.COMPATIBLE_TLS
//                )
//            )
//            .build()
//    }

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson) : Retrofit = Retrofit.Builder()
        .baseUrl("http://private-222d3-homework5.apiary-mock.com/api/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideApiService(retrofit: Retrofit) = retrofit.create(ApiService::class.java)

    @Provides
    fun provideApiHelper(apiHelper: ApiHelperImpl): ApiHelper = apiHelper

    @Singleton
    @Provides
    fun providesRoomDatabase(@ApplicationContext appContext: Context) = UserDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun providesUserDAO(userDatabase: UserDatabase) = userDatabase.getUserDAO()

}