package com.example.desafiohavan.data

import com.example.desafiohavan.domain.repository.ApiResult
import retrofit2.Response
import java.lang.Exception
import java.lang.NullPointerException


object NetworkRequester {

    /**
     * A funçâo request irá
     */

    suspend fun <T> request(api: (suspend () -> Response<T>)): ApiResult<T> = try {
        val result = api()
        if (result.isSuccessful) {
            ApiResult.Success(result.body() ?: throw NullPointerException("body is null"))
        } else
            throw when (result.code()) {
                in 400..499 -> Exception("Bad request exception")
                in 500..599 -> Exception("Api request exception")
                else -> Exception("Internal request exception")
            }
    } catch (error: Exception) {
        ApiResult.Error(error)
    }
}