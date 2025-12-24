package com.yogi.portfolio.portfolio.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yogi.portfolio.R
import com.yogi.portfolio.databinding.ItemProductBinding
import com.yogi.portfolio.portfolio.ViewModel.WishlistViewModel
import com.yogi.portfolio.portfolio.data.API.RoomEntity.CartEntity
import com.yogi.portfolio.portfolio.data.API.RoomEntity.ProductEntity

class ProductAdapter(private val wishlistViewModel: WishlistViewModel,
                     private val items: MutableList<ProductEntity>, private val onItemClick : (ProductEntity) -> Unit,
                     var onAddCartClick: (CartEntity) -> Unit) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val item = items[position]

        holder.binding.apply {
            tvTitle.text = item.title
            tvPrice.text = "₹%.2f".format(item.price)

            Glide.with(root.context)
                .load(item.image)
                .into(imgProduct)


            root.setOnClickListener {
                    onItemClick(item)
            }

            btnAddCart.setOnClickListener {
                val cart = CartEntity(
                    productId = item.id,
                    title = item.title,
                    price = item.price,
                    image = item.image,
                    quantity = 1)
                onAddCartClick(cart)
            }

            val isWishlisted = wishlistViewModel.isWishlisted(item.id)

            holder.binding.imgWishlist.setImageResource(
                if (isWishlisted) R.drawable.ic_heart_filled
                else R.drawable.ic_heart_outline
            )

            holder.binding.imgWishlist.setOnClickListener {
                wishlistViewModel.toggleWishlist(item)
                notifyItemChanged(position)
            }

            holder.binding.imgWishlist.animate()
                .scaleX(1.2f)
                .scaleY(1.2f)
                .setDuration(150)
                .withEndAction {
                    holder.binding.imgWishlist.animate()
                        .scaleX(1f)
                        .scaleY(1f)
                        .duration = 150
                }
        }
    }

    override fun getItemCount() = items.size

    fun updateData(newList: List<ProductEntity>) {
        items.clear()
        items.addAll(newList)
        notifyDataSetChanged()
    }
}