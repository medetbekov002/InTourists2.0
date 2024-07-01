package com.dev.intourist.ui.screen.home.adapters.vp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.dev.intourist.databinding.ItemTourImageForVpBinding

class VPAdapter(private val list: List<Int>) : RecyclerView.Adapter<VPAdapter.VPViewHolder>() {

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
        fun bind(image: Int) {
            binding.apply {
                imgTour.setImageResource(image)
            }
        }
    }
}