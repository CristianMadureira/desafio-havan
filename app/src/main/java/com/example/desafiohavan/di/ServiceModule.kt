package com.example.desafiohavan.di

import com.example.desafiohavan.data.network.ShoppingApiService
import com.example.desafiohavan.domain.repository.ApiResult
import org.koin.dsl.module

val serviceModule = module {

    single { ShoppingApiService() }
}