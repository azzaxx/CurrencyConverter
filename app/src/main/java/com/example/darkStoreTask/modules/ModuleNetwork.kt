package com.example.darkStoreTask.modules

import android.util.Log
import com.example.darkStoreTask.network.BASE_URL
import com.example.darkStoreTask.network.WebService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class ModuleNetwork {
    @Provides
    fun provideWebService() : WebService {
        return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .client(
                OkHttpClient().newBuilder().addInterceptor(HttpLoggingInterceptor { message ->
                    Log.d("CURRENCY_CALC", message)
                }.also {
                    it.level = HttpLoggingInterceptor.Level.BODY
                }).build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WebService::class.java)
    }
}