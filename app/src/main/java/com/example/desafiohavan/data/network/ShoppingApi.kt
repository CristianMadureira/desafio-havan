package com.example.desafiohavan.data.network

import com.example.desafiohavan.data.ShoppingItemResponse
import com.example.desafiohavan.domain.entities.ShoppingItem
import retrofit2.Response
import retrofit2.http.GET

interface ShoppingApi {

    @GET("products.json")
    suspend fun getAll() : Response<List<ShoppingItemResponse>>
}