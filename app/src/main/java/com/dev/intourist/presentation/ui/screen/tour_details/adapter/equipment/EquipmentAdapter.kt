package com.dev.intourist.presentation.ui.screen.tour_details.adapter.equipment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dev.intourist.data.remote.dtos.tours.ToursModel
import com.dev.intourist.databinding.ItemDescriptionBinding

class EquipmentAdapter(
    private val list: List<ToursModel.Result.Equipment>,
    private val image: Int
) :
    RecyclerView.Adapter<EViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EViewHolder {
        return EViewHolder(
            ItemDescriptionBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: EViewHolder, position: Int) {
        holder.bind(list[position], image)
    }
}

class EViewHolder(private val binding: ItemDescriptionBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: ToursModel.Result.Equipment, image: Int) {
        binding.tvDesc.text = item.title
        binding.img.setImageResource(image)
    }
}