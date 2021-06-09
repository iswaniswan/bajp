package com.iswan.main.movflix.data.source.remote.rest

import com.iswan.main.movflix.BuildConfig

sealed class ApiResponse<out R> {

    data class Success<out T>(val data: T): ApiResponse<T>()
    data class Error(val message: String): ApiResponse<Nothing>()
    object Empty: ApiResponse<Nothing>()

}