package com.example.desafiohavan.presentation

import android.app.Activity
import android.os.Bundle
import android.widget.ImageView
import com.example.desafiohavan.R

class ActivityTeste: Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.teste)

        val imagem = findViewById<ImageView>(R.id.imagem_produto)

    }


}