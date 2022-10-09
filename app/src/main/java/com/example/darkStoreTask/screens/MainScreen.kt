package com.example.darkStoreTask.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.darkStoreTask.models.MainViewModel
import com.example.darkStoreTask.BaseResult
import com.example.darkStoreTask.data.currencyItems
import com.example.darkStoreTask.uis.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun MainScreenComposable(
    navController: NavHostController
) {
    val viewModel: MainViewModel = hiltViewModel()
    val currencyResult = viewModel.currencyResult.observeAsState()
    var date by remember { mutableStateOf(LocalDate.now()) }
    var sellAmount by remember { mutableStateOf<Int?>(null) }
    var sellCurrency by remember { mutableStateOf(currencyItems.first()) }
    var buyCurrency by remember { mutableStateOf(currencyItems.first()) }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        CalendarLayout(date) {
            date = it
        }
        CurrencyRow(
            if (sellAmount == null) "" else sellAmount.toString(),
            "Обміняти"
        ) {
            sellAmount = if (it.matches("-?[0-9]+(\\.[0-9]+)?".toRegex())) {
                it.toInt()
            } else {
                null
            }
        }
        DropDownCurrency("Продати", sellCurrency, currencyItems) {
            sellCurrency = it
        }
        DropDownCurrency("Прідбати", buyCurrency, currencyItems) {
            buyCurrency = it
        }
        ButtonView("Обминяти") {
            sellAmount?.let { sellValue ->
                viewModel.getCurrency(
                    date.format(DateTimeFormatter.ofPattern("yyyyMMdd")),
                    sellValue,
                    sellCurrency,
                    buyCurrency
                )
            }
        }
        currencyResult.value?.let { result ->
            when (result) {
                is BaseResult.Done -> {
                    TextViewCenter(result.value)
                }
                is BaseResult.Error -> {
                    TextViewCenter(result.error)
                }
                else -> {
                    LinearProgress()
                }
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(0.dp, 0.dp, 0.dp, 16.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ButtonView("Історія") {
                navController.navigate(Screen.HistoryScreen.name)
            }
        }

    }
}