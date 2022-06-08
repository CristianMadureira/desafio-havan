package com.example.desafiohavan.presentation.shopping

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.desafiohavan.databinding.FavoriteFragmentBinding
import com.example.desafiohavan.domain.entities.ShoppingItem
import com.example.desafiohavan.domain.repository.ApiResult
import com.example.desafiohavan.presentation.shopping.adapter.ProductsShoppingAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class FavoritesFragment : Fragment() {

    private val productAdapter = ProductsShoppingAdapter()
    private lateinit var binding: FavoriteFragmentBinding
    private val shoppingViewModel: ShoppingViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FavoriteFragmentBinding.inflate(layoutInflater, container, false)
        val root = binding.root

        setUpRecycler()

        viewLifecycleOwner.lifecycleScope.launch {
            shoppingViewModel.favoritesList.collectLatest {
                when (it) {
                    is ApiResult.Success<List<ShoppingItem>> -> productAdapter.submitList(it.data)
                    is ApiResult.Error<*> -> Toast.makeText(
                        requireContext(),
                        "Ocorreu um erro inesperado: ${it.exception.message})",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }

        return root
    }

    private fun setUpRecycler() {
        productAdapter.run {
            isEnabledFavButton(false)
            setFavoriteClickListener { shoppingItem ->
                shoppingViewModel.deleteFavoriteProduct(shoppingItem)
            }
        }
        binding.recyclerProducts.run {
            layoutManager = LinearLayoutManager(context)
            adapter = productAdapter
        }
    }
}