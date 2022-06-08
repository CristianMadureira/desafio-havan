package com.example.desafiohavan.presentation.shopping.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.desafiohavan.R
import com.example.desafiohavan.domain.entities.ShoppingItem

class ProductsShoppingAdapter :
    ListAdapter<ShoppingItem, ProductsShoppingAdapter.ShoppingItemViewHolder>(ShoppingItemDiffUtils) {

    private var isEnabledFavAction = true

    private var favoriteClickListener: ((ShoppingItem) -> Unit)? = null
    private var onItemClickListener: ((ShoppingItem) -> Unit)? = null

    fun setFavoriteClickListener(click: (ShoppingItem) -> Unit) {
        this.favoriteClickListener = click
    }


    fun setOnItemClickListener(click: (ShoppingItem) -> Unit) {
        this.onItemClickListener = click
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingItemViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.shopping_item_adapter, parent, false)
        return ShoppingItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShoppingItemViewHolder, position: Int) {
        holder.bindItem(getItem(position))
    }

    fun isEnabledFavButton(isEnabled: Boolean) {
        isEnabledFavAction = isEnabled
    }

    inner class ShoppingItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        private val nameProduct = view.findViewById<TextView>(R.id.nameProduct)
        private val brand = view.findViewById<TextView>(R.id.brand_product)
        private val price = view.findViewById<TextView>(R.id.price_product)
        private val description = view.findViewById<TextView>(R.id.description_product)
        private val imageView = view.findViewById<ImageView>(R.id.image_product)
        private val cad = view.findViewById<TextView>(R.id.priceSign)
        val favoriteButton = view.findViewById<ImageButton>(R.id.add_favorite)
        val removeFavoriteButton = view.findViewById<ImageButton>(R.id.remove_favorite)

        fun bindItem(shoppingItem: ShoppingItem) {
            view.setOnClickListener {
                onItemClickListener?.invoke(shoppingItem)
            }
            nameProduct.text = shoppingItem.name
            brand.text = shoppingItem.brand
            price.text = shoppingItem.price
            description.text = shoppingItem.description
            cad.text = shoppingItem.priceSign
            favoriteButton.setOnClickListener {
                favoriteClickListener?.invoke(shoppingItem)
            }
            removeFavoriteButton.setOnClickListener {
                favoriteClickListener?.invoke(shoppingItem)

            }
            favoriteButton.isVisible = isEnabledFavAction
            removeFavoriteButton.isVisible = isEnabledFavAction.not()

            favoriteButton.setImageDrawable(
                if (shoppingItem.isFavorite)
                    ContextCompat.getDrawable(view.context, R.drawable.ic_favorite_24)
                else
                    ContextCompat.getDrawable(view.context, R.drawable.ic_favorite_border)
            )

            Glide.with(view.context)
                .load(shoppingItem.imageLink)
                .error(R.drawable.ic_image_not_found)
                .into(imageView)
        }


    }
}

object ShoppingItemDiffUtils : DiffUtil.ItemCallback<ShoppingItem>() {
    override fun areItemsTheSame(oldItem: ShoppingItem, newItem: ShoppingItem): Boolean =
        (oldItem.id == newItem.id)

    override fun areContentsTheSame(oldItem: ShoppingItem, newItem: ShoppingItem): Boolean =
        oldItem == newItem
}