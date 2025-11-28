package com.yogi.portfolio.portfolio.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yogi.portfolio.databinding.ItemCartBinding
import com.yogi.portfolio.portfolio.data.API.RoomEntity.CartEntity

class CartAdapter(
    private val onPlusClick: (Int) -> Unit,
    private val onMinusClick: (Int) -> Unit,
    private val onDeleteClick: (Int) -> Unit
) : ListAdapter<CartEntity, CartAdapter.CartViewHolder>(DiffCallback()) {

    inner class CartViewHolder(
        private val binding: ItemCartBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(cart: CartEntity) {
            binding.tvName.text = cart.title
            binding.tvPrice.text = "₹ ${cart.price}"
            binding.tvQuantity.text = cart.quantity.toString()

            Glide.with(binding.root.context)
                .load(cart.image)
                .into(binding.imgProduct)

            binding.btnIncrease.setOnClickListener {
                onPlusClick(cart.productId)
            }

            binding.btnDecrease.setOnClickListener {
                onMinusClick(cart.productId)
            }

            binding.btnDelete.setOnClickListener {
                onDeleteClick(cart.productId)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = ItemCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class DiffCallback : DiffUtil.ItemCallback<CartEntity>() {
        override fun areItemsTheSame(oldItem: CartEntity, newItem: CartEntity): Boolean {
            return oldItem.productId == newItem.productId
        }

        override fun areContentsTheSame(oldItem: CartEntity, newItem: CartEntity): Boolean {
            return oldItem == newItem
        }
    }
}