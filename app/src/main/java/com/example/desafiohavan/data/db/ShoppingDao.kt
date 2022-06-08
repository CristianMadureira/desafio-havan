package com.example.desafiohavan.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ShoppingDao {

    @Insert
    suspend fun insertFavoriteProduct(product: ProductEntity)

    @Update
    suspend fun updateProduct(product: ProductEntity)

    @Insert
    suspend fun insertAllItems(product: List<ProductEntity>)

    @Query("DELETE from products WHERE id = :id")
    suspend fun deleteFavoriteProduct(id: String)

    @Query("SELECT * from products ORDER BY name ASC")
    fun getAllFavoritedProducts(): Flow<List<ProductEntity>>

    @Query("SELECT * from products")
    fun getAllProducts(): Flow<List<ProductEntity>>

}