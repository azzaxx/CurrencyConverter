package com.example.darkStoreTask.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.darkStoreTask.BaseResult
import com.example.darkStoreTask.data.HistoryEntity
import com.example.darkStoreTask.repository.StorageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val storageRepository: StorageRepository
) : ViewModel() {
    private val _historyList: MutableLiveData<BaseResult<List<HistoryEntity>>> = MutableLiveData()
    val historyList: LiveData<BaseResult<List<HistoryEntity>>> = _historyList

    init {
        getHistory()
    }

    private fun getHistory() {
        viewModelScope.launch {
            _historyList.value = storageRepository.getOldData()
        }
    }
}