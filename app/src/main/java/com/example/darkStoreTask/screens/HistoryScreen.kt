package com.example.darkStoreTask.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.darkStoreTask.BaseResult
import com.example.darkStoreTask.data.HistoryEntity
import com.example.darkStoreTask.models.HistoryViewModel
import com.example.darkStoreTask.uis.TextViewHeader
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun HistoryScreenComposable() {
    val viewModel: HistoryViewModel = hiltViewModel()
    val historyListResult = viewModel.historyList.observeAsState()

    Scaffold { padding ->
        LazyColumn(modifier = Modifier.padding(padding)) {
            if (historyListResult.value is BaseResult.Done) {
                (historyListResult.value as BaseResult.Done<List<HistoryEntity>>)
                    .value
                    .sortedBy { it.id }
                    .forEach { history ->
                        item {
                            Card(
                                shape = RoundedCornerShape(8.dp),
                                modifier = Modifier
                                    .padding(8.dp, 2.dp)
                                    .fillMaxWidth()
                            ) {
                                Column {
                                    TextViewHeader(
                                        text = LocalDate.parse(
                                            history.date,
                                            DateTimeFormatter.ofPattern("yyyyMMdd")
                                        )
                                            .format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
                                    )
                                    Text(
                                        text = "${history.sellAmount} ${history.sellCurrency} -> ${history.buyCurrency} ${history.buyAmount}",
                                        modifier = Modifier.padding(8.dp, 4.dp),
                                        fontWeight = FontWeight.Medium,
                                        color = Color.Gray
                                    )
                                }
                            }
                        }
                    }
            }
        }
    }
}