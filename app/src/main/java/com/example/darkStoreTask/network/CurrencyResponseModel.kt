package com.example.darkStoreTask.network

import com.google.gson.annotations.SerializedName

class CurrencyResponseModel(
    @SerializedName("exchangedate")
    val exchangeDate: String,
    @SerializedName("txt")
    val currencyName: String,
    @SerializedName("rate")
    val rate: Float,
    @SerializedName("units")
    val units: Int,
    @SerializedName("rate_per_unit")
    val ratePerUnit: Float,
    @SerializedName("calcdate")
    val calDate: String,
    @SerializedName("r030")
    val currencyCode: Int
)