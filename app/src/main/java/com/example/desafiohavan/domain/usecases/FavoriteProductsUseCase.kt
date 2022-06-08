package com.example.desafiohavan.domain.usecases

import com.example.desafiohavan.domain.entities.ShoppingItem
import com.example.desafiohavan.domain.repository.ProductsRepository

class FavoriteProductsUseCase(private val productsRepository: ProductsRepository) {
    suspend operator fun invoke(shoppingItem: ShoppingItem) =
        productsRepository.favoriteProduct(shoppingItem)
}