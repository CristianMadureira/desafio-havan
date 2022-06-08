package com.example.desafiohavan.presentation.shopping

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.desafiohavan.R
import com.example.desafiohavan.databinding.HomeFragmentBinding
import com.example.desafiohavan.domain.repository.ApiResult
import com.example.desafiohavan.presentation.shopping.adapter.ProductsShoppingAdapter
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class HomeFragment : Fragment() {

    private val shoppingViewModel: ShoppingViewModel by sharedViewModel()
    private val productAdapter = ProductsShoppingAdapter()
    private lateinit var binding: HomeFragmentBinding
    private var getProductsJob: Job? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = HomeFragmentBinding.inflate(layoutInflater, container, false)
        val root = binding.root
        getCurrentStackEntry()
        setupObserves()
        setListeners()
        setUpRecycler()
        getList()
        return root
    }

    private fun getCurrentStackEntry() {
        setFragmentResultListener(FilterBottomSheetFragment.FILTER_FLAG) { requestKey, bundle ->
            if(requestKey == FilterBottomSheetFragment.FILTER_FLAG)
                shoppingViewModel.setFilter(
                    getFilterByConstant(bundle.getString(FilterBottomSheetFragment.FILTER_FLAG))
                )
        }
    }

    private fun getFilterByConstant(flagArgs: String?): ShoppingViewModel.Filters =
        when (flagArgs) {
            FilterBottomSheetFragment.FILTER_AZ -> ShoppingViewModel.Filters.Az
            FilterBottomSheetFragment.FILTER_ZA -> ShoppingViewModel.Filters.Za
            FilterBottomSheetFragment.FILTER_PRICE -> ShoppingViewModel.Filters.Price
            FilterBottomSheetFragment.FILTER_RATING -> ShoppingViewModel.Filters.Rating
            else -> ShoppingViewModel.Filters.NoFilter
        }

    private fun setupObserves() {
        viewLifecycleOwner.lifecycleScope.launch {
            shoppingViewModel.filter.collectLatest {
                getList()
            }
        }
    }

    private fun setListeners() {
        binding.filterButton.setOnClickListener {
            openFilterDialog()
        }
    }

    private fun openFilterDialog() {
        findNavController().navigate(R.id.filter_bottom_sheet)
    }

    private fun setUpRecycler() {
        productAdapter.setFavoriteClickListener { shoppingItem ->
            shoppingViewModel.insertFavoriteProduct(shoppingItem)
        }
        binding.recyclerProducts.run {
            adapter = productAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun getList() {
        getProductsJob?.cancel()
        getProductsJob = shoppingViewModel.viewModelScope.launch {
            shoppingViewModel.getShoppingItems()
                .onStart {
                    binding.progress.isVisible = true
                    binding.recyclerProducts.isVisible = false
                }
                .onCompletion {
                    binding.progress.isVisible = false
                    binding.recyclerProducts.isVisible = true

                }
                .collectLatest { list ->
                    productAdapter.stateRestorationPolicy =
                        RecyclerView.Adapter.StateRestorationPolicy.PREVENT
                    productAdapter.submitList(list)
                    binding.recyclerProducts.layoutManager?.scrollToPosition(0)

                }
        }
    }

}