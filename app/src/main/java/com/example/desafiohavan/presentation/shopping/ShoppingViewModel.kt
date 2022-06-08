package com.example.desafiohavan.presentation.shopping

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.desafiohavan.data.db.ProductEntity
import com.example.desafiohavan.domain.entities.ShoppingItem
import com.example.desafiohavan.domain.repository.ApiResult
import com.example.desafiohavan.domain.usecases.FavoriteProductsUseCase
import com.example.desafiohavan.domain.usecases.GetFavoritesProductsUseCase
import com.example.desafiohavan.domain.usecases.GetProductsUseCase
import com.example.desafiohavan.domain.usecases.UnFavoriteProductsUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class ShoppingViewModel(
    private val getProductsUseCase: GetProductsUseCase,
    private val getFavoritesProductsUseCase: GetFavoritesProductsUseCase,
    private val favoriteProductsUseCase: FavoriteProductsUseCase,
    private val unFavoriteProductsUseCase: UnFavoriteProductsUseCase
) : ViewModel() {

    val favoritesList = getFavoritesProductsUseCase()
    private val _filterState = MutableStateFlow(Filters.NoFilter)
    val filter = _filterState.asStateFlow()



    fun insertFavoriteProduct(item: ShoppingItem) = viewModelScope.launch {
        favoriteProductsUseCase(item)
    }

    fun deleteFavoriteProduct(item: ShoppingItem) = viewModelScope.launch {
        unFavoriteProductsUseCase(item)
    }


    fun getShoppingItems() =
        getProductsUseCase().map {
            if(it is ApiResult.Success)
                mapListFiltered(filter.value, it.data)
            else
                emptyList()
        }

    private fun mapListFiltered(filter: Filters, data: List<ShoppingItem>) = when(filter) {
        Filters.NoFilter -> data
        Filters.Az -> data.sortedBy { it.name }
        Filters.Za -> data.sortedByDescending { it.name }
        Filters.Price -> data.sortedBy { it.price }
        Filters.Rating -> data.sortedBy { it.rating }
    }

    fun setFilter(filter: Filters) {
        _filterState.value = filter
    }

    enum class Filters {
        NoFilter,
        Az,
        Za,
        Rating,
        Price
    }
}