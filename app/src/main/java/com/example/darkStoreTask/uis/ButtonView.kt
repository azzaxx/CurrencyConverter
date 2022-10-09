package com.example.darkStoreTask.uis

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ButtonView(label: String, onCLick: () -> Unit) {
    Button(
        modifier = Modifier
            .padding(16.dp, 8.dp, 16.dp, 8.dp),
        onClick = {
            onCLick()
        }) {
        Text(text = label)
    }
}