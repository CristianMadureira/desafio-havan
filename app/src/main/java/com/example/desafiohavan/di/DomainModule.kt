package com.example.desafiohavan.di

import com.example.desafiohavan.domain.usecases.FavoriteProductsUseCase
import com.example.desafiohavan.domain.usecases.GetFavoritesProductsUseCase
import com.example.desafiohavan.domain.usecases.GetProductsUseCase
import com.example.desafiohavan.domain.usecases.UnFavoriteProductsUseCase
import org.koin.dsl.module

    val domainModule = module {

        factory { GetProductsUseCase(get()) }
        factory { GetFavoritesProductsUseCase(get()) }
        factory { FavoriteProductsUseCase(get()) }
        factory { UnFavoriteProductsUseCase(get()) }

    }
