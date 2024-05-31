package com.dev.intourist.ui.screen.tourditail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.dev.intourist.databinding.ItemCategoriesBinding
import com.dev.intourist.databinding.ItemPromocodeBinding
import com.dev.intourist.databinding.ItemTourProgramBinding

class ProgramAdapter(private val listTime: List<String>, private val listDesc: List<String>) :
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
    override fun getItemCount() = listTime.size
    override fun onBindViewHolder(holder: ProgramViewHolder, position: Int) {
        holder.bind(listTime[position], listDesc[position])
    }
}

class ProgramViewHolder(private val binding: ItemTourProgramBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(time: String, desc: String) {
        binding.tvTime.text = time
        binding.tvProgram.text = desc
    }
}