package com.example.darkStoreTask.repository

import com.example.darkStoreTask.BaseResult
import com.example.darkStoreTask.data.HistoryEntity

interface StorageRepository {
    suspend fun saveData(historyEntityList: List<HistoryEntity>)
    suspend fun getOldData(): BaseResult<List<HistoryEntity>>
}