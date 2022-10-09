package com.example.darkStoreTask.uis

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.squaredem.composecalendar.ComposeCalendar
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun CalendarLayout(date: LocalDate, dateCallback: (LocalDate) -> Unit) {
    val showDialog = rememberSaveable { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .padding(8.dp, 8.dp, 8.dp, 8.dp)
            .fillMaxWidth()
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                showDialog.value = true
            },
    ) {
        TextViewHeader(
            text = LocalDate.parse(
                date.toString(),
                DateTimeFormatter.ofPattern("yyyy-MM-dd")
            )
                .format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
        )
        Icon(
            imageVector = Icons.Default.DateRange,
            contentDescription = null,
            modifier = Modifier
                .padding(8.dp)
                .size(20.dp, 20.dp)
                .align(Alignment.CenterEnd),
            tint = MaterialTheme.colors.onSurface
        )
    }

    if (showDialog.value) {
        ComposeCalendar(
            startDate = date,
            onDone = {
                showDialog.value = false
                dateCallback.invoke(it)
            },
            onDismiss = {
                showDialog.value = false
            }
        )
    }
}