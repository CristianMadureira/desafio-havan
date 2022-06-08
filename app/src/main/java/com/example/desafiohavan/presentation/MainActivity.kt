package com.example.desafiohavan.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.desafiohavan.R
import com.example.desafiohavan.domain.usecases.GetProductsUseCase
import com.example.desafiohavan.presentation.shopping.ShoppingViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.observeOn
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    //private val viewModel: ShoppingViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpBottonNavigation()


    }

    fun setUpBottonNavigation() {
        val navView = findViewById<BottomNavigationView>(R.id.botton_navigation)
        val navController = findNavController(R.id.navnavigation_fragment)
        navView.setupWithNavController(navController)
    }
}