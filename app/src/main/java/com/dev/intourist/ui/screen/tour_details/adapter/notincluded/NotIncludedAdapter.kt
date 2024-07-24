package com.dev.intourist.ui.screen.tour_details.adapter.notincluded

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dev.intourist.data.remote.dtos.tours.ToursModel
import com.dev.intourist.databinding.ItemDescriptionBinding

class NotIncludedAdapter (private val list: List<ToursModel.Result.NotIncluded>, private val image: Int) :
    RecyclerView.Adapter<NIViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NIViewHolder {
        return NIViewHolder(
            ItemDescriptionBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: NIViewHolder, position: Int) {
        holder.bind(list[position], image)
    }
}

class NIViewHolder(private val binding: ItemDescriptionBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: ToursModel.Result.NotIncluded, image: Int) {
        binding.tvDesc.text = item.title
        binding.img.setImageResource(image)
    }
}