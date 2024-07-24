package com.dev.intourist.ui.screen.tour_details.adapter.program

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.dev.intourist.data.remote.dtos.tours.ToursModel
import com.dev.intourist.databinding.ItemTourProgramBinding

class ProgramAdapter(private val list: List<ToursModel.Result.Program>) :
    Adapter<ProgramViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProgramViewHolder {
        return ProgramViewHolder(
            ItemTourProgramBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
    override fun getItemCount() = list.size
    override fun onBindViewHolder(holder: ProgramViewHolder, position: Int) {
        holder.bind(list[position])
    }
}

class ProgramViewHolder(private val binding: ItemTourProgramBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(tour: ToursModel.Result.Program) {
        binding.tvTime.text = tour.time
        binding.tvProgram.text = tour.title
    }
}