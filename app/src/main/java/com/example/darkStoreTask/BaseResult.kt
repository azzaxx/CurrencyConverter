package com.example.darkStoreTask

sealed class BaseResult<T> {
    class Loading<T> : BaseResult<T>()
    class Done<T>(val value: T) : BaseResult<T>()
    class Error<T>(val error: String) : BaseResult<T>()
}