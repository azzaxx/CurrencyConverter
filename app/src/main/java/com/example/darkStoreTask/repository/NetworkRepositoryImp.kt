package com.example.darkStoreTask.repository

import com.example.darkStoreTask.network.CurrencyResponseModel
import com.example.darkStoreTask.BaseResult
import com.example.darkStoreTask.network.WebService
import javax.inject.Inject

class NetworkRepositoryImp @Inject constructor(
    private val webService: WebService
) : NetworkRepository {

    override suspend fun getCurrency(date: String, code: String): BaseResult<List<CurrencyResponseModel>> {
        return try {
            BaseResult.Done(webService.getCurrency(code, date, date))
        } catch (e: Exception) {
            BaseResult.Error(e.message ?: "Unknown error")
        }
    }
}