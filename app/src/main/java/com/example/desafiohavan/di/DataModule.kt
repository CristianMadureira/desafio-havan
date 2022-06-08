package com.example.desafiohavan.di

import com.example.desafiohavan.data.ProductRepositoryImpl
import com.example.desafiohavan.data.db.ShoppingDatabase
import com.example.desafiohavan.domain.repository.ProductsRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {

    single<ProductsRepository> { ProductRepositoryImpl(get(), get()) }
}

val daoModule = module {

    single { ShoppingDatabase.getDatabase(androidContext()).shoppingDao }
}