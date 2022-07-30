package com.practicaltask.data.entity

import com.moneyme2.data.enum.APIErrorCode


sealed class APIResult<out T : Any> {
    data class Success<out T : Any>(val data: T, val message: String?) : APIResult<T>()
    data class Failure(val code: APIErrorCode, val message: String? = null) : APIResult<Nothing>()
    object InProgress : APIResult<Nothing>()
}
