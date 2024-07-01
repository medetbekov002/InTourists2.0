package com.dev.intourist.ui.screen.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.dev.intourist.databinding.ItemPromocodeBinding

class PromocodeAdapter(
    private val onClick: (promocode: PromocodeDetailsModel) -> Unit,
    private val list: List<PromocodeDetailsModel>
) : Adapter<PromocodeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PromocodeViewHolder {
        return PromocodeViewHolder(
            ItemPromocodeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: PromocodeViewHolder, position: Int) {
        holder.bind(list[position])
        holder.itemView.setOnClickListener {
            onClick(list[position])
        }
    }
}

class PromocodeViewHolder(private val binding: ItemPromocodeBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(data: PromocodeDetailsModel) {
        binding.apply {
            tvPromoTitle.text = data.title
            tvPromoDesc.text = data.description
        }
//        binding.tvPromoTitle.text = data.title
//        binding.tvPromoDesc.text = data.description
    }
}