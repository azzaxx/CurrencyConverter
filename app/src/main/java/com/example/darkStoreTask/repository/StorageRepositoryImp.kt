package com.example.darkStoreTask.repository

import android.content.Context
import android.content.SharedPreferences
import com.example.darkStoreTask.BaseResult
import com.example.darkStoreTask.data.HistoryEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.inject.Inject

private const val PREF_SAVE_KEY = "shared_prefs_save_storage_key"

class StorageRepositoryImp @Inject constructor(
    private val context: Context
) : StorageRepository {

    private fun getSharedPreferences(): SharedPreferences {
        return context.getSharedPreferences("Currency", Context.MODE_PRIVATE)
    }

    override suspend fun saveData(historyEntityList: List<HistoryEntity>) {
        getSharedPreferences()
            .edit()
            .putString(PREF_SAVE_KEY, Gson().toJson(historyEntityList))
            .apply()
    }

    override suspend fun getOldData(): BaseResult<List<HistoryEntity>> {
        val itemType = object : TypeToken<List<HistoryEntity>>() {}.type
        val data = getSharedPreferences().getString(PREF_SAVE_KEY, "")

        if (data.isNullOrEmpty()) {
            return BaseResult.Done(emptyList())
        }

        return try {
            BaseResult.Done(
                Gson().fromJson(
                    data,
                    itemType
                )
            )
        } catch (e: Exception) {
            BaseResult.Error(e.message ?: "Unknown storage error")
        }
    }
}