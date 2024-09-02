package com.dev.intourist.presentation.ui.screen.tour_details.adapter.pickuplocetions

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dev.intourist.data.remote.dtos.tours.ToursModel
import com.dev.intourist.databinding.ItemDescriptionBinding

class DetailAdapter ( private val list: List<ToursModel.Result.PickupLocation>, private val image: Int) :
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
    fun bind(item: ToursModel.Result.PickupLocation, image: Int) {
        binding.tvDesc.text = item.title
        binding.img.setImageResource(image)
    }
}