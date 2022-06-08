package com.example.desafiohavan.domain.usecases

import com.example.desafiohavan.domain.entities.ShoppingItem
import com.example.desafiohavan.domain.repository.ProductsRepository

class UnFavoriteProductsUseCase(private val productsRepository: ProductsRepository) {
    suspend operator fun invoke(shoppingItem: ShoppingItem) =
        productsRepository.unFavoriteProduct(shoppingItem)
}