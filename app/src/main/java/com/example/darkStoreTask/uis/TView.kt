package com.example.darkStoreTask.uis

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun TextViewCenter(text: String) {
    Text(
        text = text,
        modifier = Modifier
            .padding(16.dp, 8.dp, 16.dp, 8.dp),
        textAlign = TextAlign.Center
    )
}

@Composable
fun TextViewHeader(text: String) {
    Text(
        text = text,
        modifier = Modifier
            .padding(8.dp, 2.dp),
        fontStyle = FontStyle.Normal,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold
    )
}