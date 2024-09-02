    package com.dev.intourist.presentation.ui.screen.home.adapters.tour_card

    import android.content.Context
    import android.content.res.Resources
    import android.view.LayoutInflater
    import android.view.View
    import android.view.ViewGroup
    import androidx.recyclerview.widget.RecyclerView
    import com.dev.intourist.R
    import com.dev.intourist.data.remote.dtos.tours.ToursModel
    import com.dev.intourist.databinding.ItemTourCardBinding
    import com.dev.intourist.presentation.ui.model.tour_card.TourCardModel
    import com.dev.intourist.presentation.ui.screen.home.adapters.vp.VPAdapter

    class TourCardAdapter(
        private val context: Context,
        private val isHorizontal: Boolean,
        private val onClick: (tour: ToursModel.Result) -> Unit,
        private val onLikeClick: (tour: ToursModel.Result) -> Unit,

        ) :
        RecyclerView.Adapter<TourCardViewHolder>() {
        private val list: ArrayList<ToursModel.Result> = ArrayList()


        fun addData(newData: List<ToursModel.Result>) {
            val lastOne = list.size
            list.addAll(newData)
            notifyItemRangeInserted(lastOne, newData.size)
        }

        fun reloadData(newData: List<ToursModel.Result>) {
            list.clear()
            list.addAll(newData)
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TourCardViewHolder {
            val layoutInflater = LayoutInflater.from(context)
            val itemView = layoutInflater.inflate(R.layout.item_tour_card, parent, false)
            if (isHorizontal) {
                val layoutParams = itemView.layoutParams as RecyclerView.LayoutParams
                layoutParams.width = 320.dpToPx() // ширинa на 320dp
                itemView.layoutParams = layoutParams
            } else {
                /* val layoutParams = itemView.layoutParams as RecyclerView.LayoutParams
                 layoutParams.width = 320.dpToPx() // ширинa на 320dp
                 itemView.layoutParams = layoutParams*/
            }
            return TourCardViewHolder(itemView)
        }

        override fun getItemCount() = list.size

        override fun onBindViewHolder(holder: TourCardViewHolder, position: Int) {
            holder.bind(list[position], onLikeClick)
            holder.itemView.setOnClickListener {
                onClick(list[position])
            }
        }
    }

    class TourCardViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        private val binding = ItemTourCardBinding.bind(itemView)
        fun bind(tourCard: ToursModel.Result, onClick: (tour: ToursModel.Result) -> Unit) {
            binding.apply {
                /*  imgLike.setOnClickListener{
                      onClick(tourCard,adapterPosition)
                      if (tourCard.isLiked) {
                          binding.imgLike.setImageResource(R.drawable.ic_heart_liked)
                      } else {
                          binding.imgLike.setImageResource(R.drawable.ic_heart_like)
                      }
                  }*/
                tvTourTitle.text = tourCard.title
                tvTourDuration.text = tourCard.duration.title
                tvTourDates.setSingleLine()
                tvTourDates.isSelected = true
                val datesBuilder = StringBuilder()
                tourCard.tour_dates.forEach { date ->
                    if (datesBuilder.isNotEmpty()) {
                        datesBuilder.append(", ")
                    }
                    datesBuilder.append(date.date)
                }
                tvTourDates.text = datesBuilder.toString()
                tvTourPrice.text = tourCard.price
                vpTourCardImg.adapter = VPAdapter(tourCard.images)
                indicator.setViewPager(binding.vpTourCardImg)
            }
        }
    }

    fun Int.dpToPx(): Int {
        return (this * Resources.getSystem().displayMetrics.density).toInt()
    }