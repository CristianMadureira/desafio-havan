package com.example.desafiohavan.domain.usecases

import com.example.desafiohavan.domain.repository.ProductsRepository

class GetProductsUseCase(private val productsRepository: ProductsRepository) {
    operator fun invoke() = productsRepository.getProducts()
}