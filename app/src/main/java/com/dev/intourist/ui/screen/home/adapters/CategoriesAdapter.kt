package com.dev.intourist.ui.screen.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.dev.intourist.databinding.ItemCategoriesBinding

class CategoriesAdapter(private val onClick: () -> Unit, private val list: List<String>) :
    Adapter<CategoriesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        return CategoriesViewHolder(
            ItemCategoriesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        holder.bind(list[position])
        holder.itemView.setOnClickListener {
            onClick()
        }
    }
}

class CategoriesViewHolder(private val binding: ItemCategoriesBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: String) {
        binding.btnCategory.text = item
    }
}