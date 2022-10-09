package com.example.darkStoreTask.data

import com.google.gson.annotations.SerializedName

data class HistoryEntity(
    @SerializedName("id")
    val id: Long,
    @SerializedName("date")
    val date: String,
    @SerializedName("sellCurrency")
    val sellCurrency: String,
    @SerializedName("buyCurrency")
    val buyCurrency: String,
    @SerializedName("sellAmount")
    val sellAmount: String,
    @SerializedName("buyAmount")
    val buyAmount: String
)