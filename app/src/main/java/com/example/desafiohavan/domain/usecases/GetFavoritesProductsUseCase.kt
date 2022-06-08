package com.example.desafiohavan.domain.usecases

import com.example.desafiohavan.domain.repository.ProductsRepository

class GetFavoritesProductsUseCase(private val productsRepository: ProductsRepository) {
    operator fun invoke() = productsRepository.getFavoritesProducts()
}