package com.example.desafiohavan.domain.repository

import com.example.desafiohavan.domain.entities.ShoppingItem
import kotlinx.coroutines.flow.Flow
import java.lang.Exception

interface ProductsRepository {
    fun getProducts(): Flow<ApiResult<List<ShoppingItem>>>
    fun getFavoritesProducts(): Flow<ApiResult<List<ShoppingItem>>>
    suspend fun favoriteProduct(item: ShoppingItem): ApiResult<Unit>
    suspend fun unFavoriteProduct(item: ShoppingItem): ApiResult<Unit>
}

sealed class ApiResult<T> {
    data class Success<T>(val data: T) : ApiResult<T>()
    data class Error<T>(val exception: Exception) : ApiResult<T>()
}

fun <T, S> ApiResult<T>.mapSuccess(scope : T.() -> S) = when(this){
    is ApiResult.Success->ApiResult.Success(scope(data))
    is ApiResult.Error -> ApiResult.Error(this.exception)
}