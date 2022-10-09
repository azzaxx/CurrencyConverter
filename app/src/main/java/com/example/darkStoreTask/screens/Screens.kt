package com.example.darkStoreTask.screens

sealed class Screen(val name: String) {
    object MainScreen : Screen("main_screen")
    object HistoryScreen : Screen("history_screen")
}