package com.example.desafiohavan.presentation.shopping

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import com.example.desafiohavan.databinding.ShoppingDetailsFragmentBinding

class ShoppingDetailsFragment: Fragment() {

    private lateinit var binding: ShoppingDetailsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ShoppingDetailsFragmentBinding.inflate(layoutInflater, container, false)
        val root = binding.root
        return root
    }
}