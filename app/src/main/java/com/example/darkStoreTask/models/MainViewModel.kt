package com.example.darkStoreTask.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.darkStoreTask.BaseResult
import com.example.darkStoreTask.data.CurrencyData
import com.example.darkStoreTask.data.HistoryEntity
import com.example.darkStoreTask.repository.NetworkRepository
import com.example.darkStoreTask.repository.StorageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val networkRepository: NetworkRepository,
    private val storageRepository: StorageRepository
) : ViewModel() {
    private val _currencyResult: MutableLiveData<BaseResult<String>> =
        MutableLiveData()
    val currencyResult: LiveData<BaseResult<String>> = _currencyResult

    fun getCurrency(
        date: String,
        sellAmount: Int,
        sellCurrency: CurrencyData,
        buyCurrency: CurrencyData
    ) {
        _currencyResult.value = BaseResult.Loading()

        viewModelScope.launch {
            val sellCurrencyResponse = networkRepository.getCurrency(date, sellCurrency.letterCode)
            val buyCurrencyResponse = networkRepository.getCurrency(date, buyCurrency.letterCode)

            if (sellCurrencyResponse is
                        BaseResult.Done && buyCurrencyResponse is BaseResult.Done
            ) {
                val sellCurrencyData =
                    sellCurrencyResponse.value.find { it.currencyCode == sellCurrency.code }
                val buyCurrencyData =
                    buyCurrencyResponse.value.find { it.currencyCode == buyCurrency.code }

                val sellValue = sellCurrencyData?.ratePerUnit?.times(sellAmount)
                val buyValue = buyCurrencyData?.let { sellValue?.div(it.ratePerUnit) }

                _currencyResult.value = BaseResult.Done(
                    "$sellAmount ${sellCurrency.name} -> ${buyCurrency.name} $buyValue"
                )
                saveHistory(
                    HistoryEntity(
                        id = System.currentTimeMillis(),
                        date = date,
                        sellCurrency = sellCurrency.name,
                        buyCurrency = buyCurrency.name,
                        sellAmount = sellAmount.toString(),
                        buyAmount = buyValue.toString()
                    )
                )
            } else if (sellCurrencyResponse is BaseResult.Error) {
                _currencyResult.value = BaseResult.Error(sellCurrencyResponse.error)
            } else if (buyCurrencyResponse is BaseResult.Error) {
                _currencyResult.value = BaseResult.Error(buyCurrencyResponse.error)
            }
        }
    }

    private suspend fun saveHistory(historyEntity: HistoryEntity) {
        val oldItemsCallResult = storageRepository.getOldData()

        if (oldItemsCallResult is BaseResult.Done) {
            val oldItemsList = oldItemsCallResult.value.toMutableList().plus(historyEntity)
                .sortedByDescending { it.id }

            storageRepository.saveData(
                if (oldItemsList.size > 10)
                    oldItemsList.subList(0, 10)
                else oldItemsList
            )
        }
    }
}