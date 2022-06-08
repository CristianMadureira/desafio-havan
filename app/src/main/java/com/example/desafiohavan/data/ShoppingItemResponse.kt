package com.example.desafiohavan.data

import com.example.desafiohavan.domain.entities.ProductColor

data class ShoppingItemResponse (

    val api_featured_image: String,
    val brand: String? = null,
    val category: String? = null,
    val created_at: String,
    val currency: String? = null,
    val description: String? = null,
    val id: Int,
    val image_link: String,
    val name: String,
    val price: String? = null,
    val price_sign: String? = null,
    val product_api_url: String,
    val product_colors: List<ProductColor>,
    val product_link: String,
    val product_type: String,
    val rating: Double? = null,
    val tag_list: List<String>,
    val updated_at: String,
    val website_link: String
    )