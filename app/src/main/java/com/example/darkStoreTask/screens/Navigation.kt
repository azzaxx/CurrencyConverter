package com.example.darkStoreTask.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun Content() {
    val navHostController = rememberNavController()

    NavHost(
        navController = navHostController,
        startDestination = Screen.MainScreen.name
    ) {
        composable(Screen.MainScreen.name) {
            MainScreenComposable(navHostController)
        }
        composable(Screen.HistoryScreen.name) {
            HistoryScreenComposable()
        }
    }
}