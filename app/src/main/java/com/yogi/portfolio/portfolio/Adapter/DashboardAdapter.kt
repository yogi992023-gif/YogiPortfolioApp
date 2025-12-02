package com.yogi.portfolio.portfolio.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yogi.portfolio.databinding.ItemMenuBinding
import com.yogi.portfolio.portfolio.data.API.RoomEntity.MenuEntity

class DashboardAdapter(
    private var items: List<MenuEntity>,
    private val onClick: (MenuEntity) -> Unit
) : RecyclerView.Adapter<DashboardAdapter.MenuViewHolder>() {

    inner class MenuViewHolder(val binding: ItemMenuBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val binding = ItemMenuBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MenuViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        val item = items[position]

        holder.binding.menuIcon.setImageResource(item.menuIcon)
        holder.binding.menuTitle.text = item.menuName

        holder.binding.root.setOnClickListener {
            onClick(item)
        }
    }

    override fun getItemCount() = items.size

    fun updateData(newList: List<MenuEntity>) {
        items = newList
        notifyDataSetChanged()
    }
}