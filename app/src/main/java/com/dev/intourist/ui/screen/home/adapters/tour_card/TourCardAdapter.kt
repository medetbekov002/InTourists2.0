package com.dev.intourist.ui.screen.home.adapters.tour_card

import android.content.Context
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dev.intourist.R
import com.dev.intourist.databinding.ItemTourCardBinding
import com.dev.intourist.ui.model.tour_card.TourCardModel
import com.dev.intourist.ui.screen.home.adapters.vp.VPAdapter

class TourCardAdapter(
    private val context: Context,
    private val isHorizontal: Boolean,
    private val onClick: (tour: TourCardModel) -> Unit,
    private val onLikeClick: (tour: TourCardModel, position: Int) -> Unit,
    private val list: List<TourCardModel>,

    ) :
    RecyclerView.Adapter<TourCardViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TourCardViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val itemView = layoutInflater.inflate(R.layout.item_tour_card, parent, false)
        if (isHorizontal) {
            val layoutParams = itemView.layoutParams as RecyclerView.LayoutParams
            layoutParams.width = 320.dpToPx() // ширинa на 320dp
            itemView.layoutParams = layoutParams
        }else{
           /* val layoutParams = itemView.layoutParams as RecyclerView.LayoutParams
            layoutParams.width = 320.dpToPx() // ширинa на 320dp
            itemView.layoutParams = layoutParams*/
        }
        return TourCardViewHolder(itemView)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: TourCardViewHolder, position: Int) {
        holder.bind(list[position],onLikeClick)
        holder.itemView.setOnClickListener {
            onClick(list[position])
        }
    }
}

class TourCardViewHolder(itemView: View) :
    RecyclerView.ViewHolder(itemView) {
    private val binding = ItemTourCardBinding.bind(itemView)
    fun bind(tourCard: TourCardModel, onClick: (tour: TourCardModel, position: Int) -> Unit) {
        binding.apply {
            imgLike.setOnClickListener{
                onClick(tourCard,adapterPosition)
                if (tourCard.isLiked) {
                    binding.imgLike.setImageResource(R.drawable.ic_heart_liked)
                } else {
                    binding.imgLike.setImageResource(R.drawable.ic_heart_like)
                }
            }
            tvTourDuration.text = tourCard.duration
            tvTourDates.setSingleLine()
            tvTourDates.isSelected = true
            tvTourDates.text = tourCard.dates
            tvTourPrice.text = tourCard.price
            vpTourCardImg.adapter = VPAdapter(tourCard.list)
            indicator.setViewPager(binding.vpTourCardImg)
        }
//        binding.imgLike.setOnClickListener{
//            onClick(tourCard,adapterPosition)
//            if (tourCard.isLiked) {
//                binding.imgLike.setImageResource(R.drawable.ic_heart_liked)
//            } else {
//                binding.imgLike.setImageResource(R.drawable.ic_heart_like)
//            }
//        }

//        binding.tvTourDuration.text = tourCard.duration
//        binding.tvTourDates.setSingleLine()
//        binding.tvTourDates.isSelected = true
//        binding.tvTourDates.text = tourCard.dates
//        binding.tvTourPrice.text = tourCard.price
//        binding.vpTourCardImg.adapter = VPAdapter(tourCard.list)
//        binding.indicator.setViewPager(binding.vpTourCardImg)
    }

}

fun Int.dpToPx(): Int {
    return (this * Resources.getSystem().displayMetrics.density).toInt()
}