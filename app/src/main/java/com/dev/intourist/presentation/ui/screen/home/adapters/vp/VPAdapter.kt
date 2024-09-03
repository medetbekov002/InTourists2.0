package com.dev.intourist.presentation.ui.screen.home.adapters.vp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.dev.intourist.data.remote.dtos.tours.ToursModel
import com.dev.intourist.databinding.ItemTourImageForVpBinding
import com.dev.intourist.presentation.extensions.loadImage

class VPAdapter(private val list: List<ToursModel.Result.Image>) : RecyclerView.Adapter<VPAdapter.VPViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VPViewHolder {
        return VPViewHolder(
            ItemTourImageForVpBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: VPViewHolder, position: Int) {
        holder.bind(list[position])
    }

    class VPViewHolder(private val binding: ItemTourImageForVpBinding) : ViewHolder(binding.root) {
        fun bind(image: ToursModel.Result.Image) {
            binding.apply {
                imgTour.loadImage(image.image)
            }
        }
    }
}