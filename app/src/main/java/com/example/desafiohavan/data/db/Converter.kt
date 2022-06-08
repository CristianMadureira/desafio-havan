package com.example.desafiohavan.data.db

import androidx.room.TypeConverter
import com.example.desafiohavan.domain.entities.ProductColor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

class Converter {

    @TypeConverter
    fun productListForString(value: List<ProductColor>): String {
        val gson = GsonBuilder().setPrettyPrinting().create()
        val jsonProduct: String = gson.toJson(value)
        return jsonProduct
    }

    @TypeConverter
    fun productStringForList(value: String): List<ProductColor> {
        val gson = Gson()
        val listType = object : TypeToken<List<ProductColor>>() {}.type
        var list: List<ProductColor> = gson.fromJson(value, listType)
        return list
    }

    @TypeConverter
    fun tagListForString(value: List<String>): String {
        val gson = GsonBuilder().setPrettyPrinting().create()
        val jsonProduct: String = gson.toJson(value)
        return jsonProduct
    }

    @TypeConverter
    fun tagStringForList(value: String): List<String> {
        val gson = Gson()
        val listType = object : TypeToken<List<String>>() {}.type
        var list: List<String> = gson.fromJson(value, listType)
        return list
    }


}