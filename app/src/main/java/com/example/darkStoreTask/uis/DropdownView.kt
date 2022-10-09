package com.example.darkStoreTask.uis

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun <T>DropDownCurrency(
    label: String,
    selected: T,
    items: List<T>,
    currencyCallback: (T) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier.padding(16.dp, 8.dp, 16.dp, 8.dp)
    ) {
        Column {
            OutlinedTextField(
                value = selected.toString(),
                onValueChange = { },
                label = {
                    Text(text = label)
                },
                modifier = Modifier.fillMaxWidth(),
                trailingIcon = {
                    Icon(Icons.Outlined.ArrowDropDown, null)
                },
                readOnly = true
            )
            DropdownMenu(
                modifier = Modifier.fillMaxWidth(),
                expanded = expanded,
                onDismissRequest = { expanded = false },
            ) {
                items.forEach { entry ->
                    DropdownMenuItem(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            currencyCallback(entry)
                            expanded = false
                        }
                    ) {
                        Text(
                            text = (entry.toString()),
                            modifier = Modifier.wrapContentWidth()
                        )
                    }
                }
            }
        }

        Spacer(
            modifier = Modifier
                .matchParentSize()
                .background(Color.Transparent)
                .padding(10.dp)
                .clickable(
                    onClick = { expanded = !expanded }
                )
        )
    }
}
