package com.example.desafiohavan.di

import com.example.desafiohavan.presentation.shopping.ShoppingViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    viewModel { ShoppingViewModel(get(), get(), get(), get()) }
}

