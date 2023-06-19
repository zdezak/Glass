package com.example.glass.data.model

sealed class ResultState<out T : Any> {
    data class Success<out T : Any>(val data: T) : ResultState<T>()
    data class Error(val error: Exception) : ResultState<Nothing>()
}

inline fun <T, R : Any> T.runCatching(block: T.() -> R): ResultState<R> {
    return try {
        ResultState.Success(block())
    } catch (e: Exception) {
        ResultState.Error(e)
    }
}