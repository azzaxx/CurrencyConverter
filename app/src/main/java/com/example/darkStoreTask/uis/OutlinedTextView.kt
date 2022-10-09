package com.example.darkStoreTask.uis

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun CurrencyRow(value: String, label: String, valueChangeCallback: (String) -> Unit) {
    Row {
        OutlinedTextField(
            value = value,
            label = {
                Text(text = label)
            },
            onValueChange = {
                valueChangeCallback(it)
            },
            singleLine = true,
            maxLines = 1,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            textStyle = TextStyle(),
            modifier = Modifier
                .weight(1f)
                .padding(16.dp, 8.dp, 16.dp, 8.dp)
        )
    }
}
