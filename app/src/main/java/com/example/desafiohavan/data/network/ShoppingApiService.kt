package com.example.desafiohavan.data.network

import com.example.desafiohavan.data.ShoppingItemResponse
import com.example.desafiohavan.domain.entities.ShoppingItem
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ShoppingApiService {

    private var BASE_URL = "https://makeup-api.herokuapp.com/api/v1/"

    private val retrofit = Retrofit.Builder()
        .client(getOkHttpClient())
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ShoppingApi::class.java)

    suspend fun getProducts(): Response<List<ShoppingItemResponse>> = retrofit.getAll()


    private fun getOkHttpClient() = OkHttpClient.Builder()
        .connectTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(10, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .build()
}