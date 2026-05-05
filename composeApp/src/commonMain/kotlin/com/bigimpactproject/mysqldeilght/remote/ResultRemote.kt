package com.bigimpactproject.mysqldeilght.remote

sealed class ResultRemote<out T> {
    data class Success<out T>(val data: T) : ResultRemote<T>()
    data class Error(val message: String, val code: Int? = null) : ResultRemote<Nothing>()
}