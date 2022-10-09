package com.example.darkStoreTask.network

import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {
    @GET("NBU_Exchange/exchange_site?&json")
    suspend fun getCurrency(
        @Query("valcode") valCode: String,
        @Query("start") start: String,
        @Query("end") end: String,
    ): List<CurrencyResponseModel>
}