package com.example.darkStoreTask.repository

import com.example.darkStoreTask.network.CurrencyResponseModel
import com.example.darkStoreTask.BaseResult

interface NetworkRepository {
    suspend fun getCurrency(date: String, code: String): BaseResult<List<CurrencyResponseModel>>
}