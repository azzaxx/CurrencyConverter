package com.example.darkStoreTask.data

data class CurrencyData(
    val code: Int,
    val letterCode: String,
    val name: String
) {
    override fun toString(): String {
        return name
    }
}