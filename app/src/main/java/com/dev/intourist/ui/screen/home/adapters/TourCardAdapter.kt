package com.dev.intourist.ui.screen.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dev.intourist.databinding.ItemTourCardBinding

class TourCardAdapter(
    private val onClick: (tour: TourCardModel) -> Unit,
    private val list: List<TourCardModel>
) :
    RecyclerView.Adapter<TourCardViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TourCardViewHolder {
        return TourCardViewHolder(
            ItemTourCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: TourCardViewHolder, position: Int) {
        holder.bind(list[position])
        holder.itemView.setOnClickListener {
            onClick(list[position])
        }
    }
}

class TourCardViewHolder(private val binding: ItemTourCardBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(tourCard: TourCardModel) {
        binding.tvTourDuration.text = tourCard.duration
        binding.tvTourDates.setSingleLine()
        binding.tvTourDates.isSelected = true
        binding.tvTourDates.text = tourCard.dates
        binding.tvTourPrice.text = tourCard.price
        binding.tvTourFullPrice.text = tourCard.fullPrice
        binding.vpTourCardImg.adapter = VPAdapter(tourCard.list)
        binding.indicator.setViewPager(binding.vpTourCardImg)
    }
}