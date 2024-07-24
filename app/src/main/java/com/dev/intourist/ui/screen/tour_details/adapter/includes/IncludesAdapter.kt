package com.dev.intourist.ui.screen.tour_details.adapter.includes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dev.intourist.data.remote.dtos.tours.ToursModel
import com.dev.intourist.databinding.ItemDescriptionBinding

class IncludesAdapter(private val list: List<ToursModel.Result.Included>, private val image: Int) :
    RecyclerView.Adapter<IViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IViewHolder {
        return IViewHolder(
            ItemDescriptionBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: IViewHolder, position: Int) {
        holder.bind(list[position], image)
    }
}

class IViewHolder(private val binding: ItemDescriptionBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: ToursModel.Result.Included, image: Int) {
        binding.tvDesc.text = item.title
        binding.img.setImageResource(image)
    }
}