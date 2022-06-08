package com.example.desafiohavan.presentation.shopping

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import com.example.desafiohavan.R
import com.example.desafiohavan.databinding.FilterBottomSheetDialogFragmentBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class FilterBottomSheetFragment : BottomSheetDialogFragment(), View.OnClickListener {
    private lateinit var binding: FilterBottomSheetDialogFragmentBinding


    companion object {
        const val FILTER_FLAG = "filter_args"
        const val FILTER_AZ = "AZ"
        const val FILTER_ZA = "ZA"
        const val FILTER_PRICE = "PRICE"
        const val FILTER_RATING = "RATING"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        FilterBottomSheetDialogFragmentBinding.inflate(inflater, container, false).also {
            binding = it
        }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.run {
            az.setOnClickListener(this@FilterBottomSheetFragment)
            za.setOnClickListener(this@FilterBottomSheetFragment)
            rating.setOnClickListener(this@FilterBottomSheetFragment)
            price.setOnClickListener(this@FilterBottomSheetFragment)
        }
    }

    override fun onClick(view: View) {
        setFragmentResult(
            FILTER_FLAG,
            bundleOf(
                FILTER_FLAG to when (view.id) {
                    binding.az.id -> FILTER_AZ
                    binding.za.id -> FILTER_ZA
                    binding.price.id -> FILTER_PRICE
                    binding.rating.id -> FILTER_RATING
                    else -> null
                }
            )
        )
        findNavController().popBackStack()
    }
}