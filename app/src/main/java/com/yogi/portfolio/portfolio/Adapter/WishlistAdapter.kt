package com.yogi.portfolio.portfolio.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yogi.portfolio.databinding.ItemWishlistBinding
import com.yogi.portfolio.portfolio.data.API.RoomEntity.WishlistEntity

class WishlistAdapter(
    private val onRemoveClick: (WishlistEntity) -> Unit
) : ListAdapter<WishlistEntity, WishlistAdapter.ViewHolder>(DiffCallback()) {

    class ViewHolder(val binding: ItemWishlistBinding) :
        RecyclerView.ViewHolder(binding.root)

    class DiffCallback : DiffUtil.ItemCallback<WishlistEntity>() {
        override fun areItemsTheSame(oldItem: WishlistEntity, newItem: WishlistEntity) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: WishlistEntity, newItem: WishlistEntity) =
            oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemWishlistBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        holder.binding.tvTitle.text = item.title
        holder.binding.tvPrice.text = "₹${item.price}"

        Glide.with(holder.itemView.context)
            .load(item.image)
            .into(holder.binding.imgProduct)

        holder.binding.imgRemove.setOnClickListener {
            onRemoveClick(item)
        }
    }
}