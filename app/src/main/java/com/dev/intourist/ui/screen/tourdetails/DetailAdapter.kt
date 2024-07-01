package com.dev.intourist.ui.screen.tourdetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dev.intourist.databinding.ItemDescriptionBinding

class DetailAdapter ( private val list: List<String>, private val image: Int) :
    RecyclerView.Adapter<DitailsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DitailsViewHolder {
        return DitailsViewHolder(
            ItemDescriptionBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: DitailsViewHolder, position: Int) {
        holder.bind(list[position], image)
    }
}

class DitailsViewHolder(private val binding: ItemDescriptionBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: String, image: Int) {
        binding.tvDesc.text = item
        binding.img.setImageResource(image)
    }
}