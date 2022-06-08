package com.example.desafiohavan.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.desafiohavan.domain.entities.ProductColor

@Entity(tableName = "products")
data class ProductEntity(

    val apiFeatureImage: String,
    val brand: String,
    val category: String,
    val createdAt: String,
    val currency: String,
    val description: String,
    @PrimaryKey
    val id: Int,
    val imageLink: String,
    val name: String,
    val price: String,
    val priceSign: String,
    val productApiUrl: String,
    val productColors: List<ProductColor>,
    val productLink: String,
    val productType: String,
    val rating: Double,
    val tagList: List<String>,
    val updatedAt: String,
    val websiteLink: String,
    var isFavorited: Boolean = false
)