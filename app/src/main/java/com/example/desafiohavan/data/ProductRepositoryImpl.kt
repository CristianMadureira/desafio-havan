package com.example.desafiohavan.data


import com.example.desafiohavan.data.db.ProductEntity
import com.example.desafiohavan.data.db.ShoppingDao
import com.example.desafiohavan.data.network.ShoppingApiService
import com.example.desafiohavan.domain.entities.ShoppingItem
import com.example.desafiohavan.domain.repository.ApiResult
import com.example.desafiohavan.domain.repository.ProductsRepository
import com.example.desafiohavan.domain.repository.mapSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.lang.Exception

class ProductRepositoryImpl(private val service: ShoppingApiService, private val dao: ShoppingDao) :
    ProductsRepository {

    override fun getProducts() = flow {
        NetworkRequester.request(api = service::getProducts).mapSuccess {
            map {
                ShoppingItem(
                    it.api_featured_image,
                    it.brand ?: "",
                    it.category ?: "",
                    it.created_at,
                    it.currency ?: "",
                    it.description ?: "",
                    it.id,
                    it.image_link,
                    it.name,
                    it.price ?: "",
                    it.price_sign ?: "",
                    it.product_api_url,
                    it.product_colors,
                    it.product_link,
                    it.product_type,
                    it.rating ?: 0.0,
                    it.tag_list,
                    it.updated_at,
                    it.website_link
                )
            }
        }.apply {
            if (this is ApiResult.Success)
                dao.insertAllItems(data.map { item ->
                    ProductEntity(
                        item.apiFeatureImage,
                        item.brand,
                        item.category,
                        item.createdAt,
                        item.currency,
                        item.description,
                        item.id,
                        item.imageLink,
                        item.name,
                        item.price,
                        item.priceSign,
                        item.productApiUrl,
                        item.productColors,
                        item.productLink,
                        item.productType,
                        item.rating,
                        item.tagList,
                        item.updatedAt,
                        item.websiteLink
                    )
                })
        }
        try {
            emitAll(
                dao.getAllProducts().map {
                    ApiResult.Success(it.map {
                            ShoppingItem(
                                it.apiFeatureImage,
                                it.brand,
                                it.category,
                                it.createdAt,
                                it.currency,
                                it.description,
                                it.id,
                                it.imageLink,
                                it.name,
                                it.price,
                                it.priceSign,
                                it.productApiUrl,
                                it.productColors,
                                it.productLink,
                                it.productType,
                                it.rating,
                                it.tagList,
                                it.updatedAt,
                                it.websiteLink,
                                it.isFavorited
                            )
                        })
                }
            )
        } catch (error: Exception) {
            emit(
                ApiResult.Error(error)
            )
        }
    }

    override fun getFavoritesProducts(): Flow<ApiResult<List<ShoppingItem>>> = flow {
        try {
            val result = dao.getAllFavoritedProducts()
            emitAll(
                result.map { productEntities ->
                    ApiResult.Success(
                        productEntities.map {
                            ShoppingItem(
                                it.apiFeatureImage,
                                it.brand,
                                it.category,
                                it.createdAt,
                                it.currency,
                                it.description,
                                it.id,
                                it.imageLink,
                                it.name,
                                it.price,
                                it.priceSign,
                                it.productApiUrl,
                                it.productColors,
                                it.productLink,
                                it.productType,
                                it.rating,
                                it.tagList,
                                it.updatedAt,
                                it.websiteLink,
                                it.isFavorited
                            )
                        }
                    )
                }
            )
        } catch (error: Exception) {
            emit(ApiResult.Error(error))
        }
    }

    override suspend fun favoriteProduct(item: ShoppingItem) = withContext(Dispatchers.IO) {
        try {
            val result = ProductEntity(
                item.apiFeatureImage,
                item.brand,
                item.category,
                item.createdAt,
                item.currency,
                item.description,
                item.id,
                item.imageLink,
                item.name,
                item.price,
                item.priceSign,
                item.productApiUrl,
                item.productColors,
                item.productLink,
                item.productType,
                item.rating,
                item.tagList,
                item.updatedAt,
                item.websiteLink,
                true
            )
            dao.updateProduct(result)
            ApiResult.Success(Unit)

        } catch (error: Exception) {
            ApiResult.Error(error)
        }
    }

    override suspend fun unFavoriteProduct(item: ShoppingItem) = withContext(Dispatchers.IO) {

        try {
            val result = ProductEntity(
                item.apiFeatureImage,
                item.brand,
                item.category,
                item.createdAt,
                item.currency,
                item.description,
                item.id,
                item.imageLink,
                item.name,
                item.price,
                item.priceSign,
                item.productApiUrl,
                item.productColors,
                item.productLink,
                item.productType,
                item.rating,
                item.tagList,
                item.updatedAt,
                item.websiteLink,
                false
            )
            dao.updateProduct(result)
            ApiResult.Success(Unit)

        } catch (error: Exception) {
            ApiResult.Error(error)
        }
    }

}