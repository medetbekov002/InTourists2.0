package com.dev.intourist.presentation.ui.screen.favorite

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.dev.intourist.R
import com.dev.intourist.databinding.FragmentFavoriteBinding
import com.dev.intourist.presentation.base.fragment.BaseFragment
import com.dev.intourist.presentation.ui.screen.home.adapters.tour_card.TourCardAdapter
import com.dev.intourist.presentation.ui.model.tour_card.TourCardModel
import com.google.android.gms.maps.GoogleMap
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteFragment : BaseFragment<FragmentFavoriteBinding, FavoriteViewModel>(R.layout.fragment_favorite) {
//    private lateinit var binding: FragmentFavoriteBinding
    override val binding: FragmentFavoriteBinding by viewBinding(FragmentFavoriteBinding::bind)
    override val viewModel: FavoriteViewModel by viewModel()

    private val list1 = listOf(
        R.drawable.image_view_pager,
        R.drawable.image_view_pager,
        R.drawable.image_view_pager,
        R.drawable.image_view_pager,
        R.drawable.image_view_pager,
        R.drawable.image_view_pager
    )

    private val listTour = listOf(
        TourCardModel(
            list1,
            "Ущелье Ала-Арча", "1900 c.", "1200 c.", "Однодневный тур",
            "15 мая, 16 мая, 17 мая, 18 мая, 19 мая, 20 мая, 21 мая", false
        ),
        TourCardModel(
            list1,
            "Ущелье Ала-Арча", "1900 c.", "1200 c.", "Однодневный тур",
            "15 мая, 16 мая, 17 мая, 18 мая, 19 мая, 20 мая, 21 мая", false
        ),
        TourCardModel(
            list1,
            "Ущелье Ала-Арча", "1900 c.", "1200 c.", "Однодневный тур",
            "15 мая, 16 мая, 17 мая, 18 мая, 19 мая, 20 мая, 21 мая", false
        ),
        TourCardModel(
            list1,
            "Ущелье Ала-Арча", "1900 c.", "1200 c.", "Однодневный тур",
            "15 мая, 16 мая, 17 мая, 18 мая, 19 мая, 20 мая, 21 мая", false
        ),
        TourCardModel(
            list1,
            "Ущелье Ала-Арча", "1900 c.", "1200 c.", "Однодневный тур",
            "15 мая, 16 мая, 17 мая, 18 мая, 19 мая, 20 мая, 21 мая", false
        ),
        TourCardModel(
            list1,
            "Ущелье Ала-Арча", "1900 c.", "1200 c.", "Однодневный тур",
            "15 мая, 16 мая, 17 мая, 18 мая, 19 мая, 20 мая, 21 мая", false
        )
    )

//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
//        return binding.root
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
      /*  val adapter =
            TourCardAdapter(requireContext(), false, this::onItemClick, this::onLikeClick, listTour)*/

        binding.apply {
            //rvMyTours.adapter = adapter

            ivFilters.setOnClickListener {
                findNavController().navigate(R.id.fragment_filters)
            }
            svSearchTours.setOnClickListener {
                findNavController().navigate(R.id.fragment_search)
            }
        }
//
//        // Filter button click listener
//        binding.ivFilters.setOnClickListener {
//            findNavController().navigate(R.id.fragment_filters)
//        }
//
//        // Search button click listener
//        binding.svSearchTours.setOnClickListener {
//            findNavController().navigate(R.id.fragment_search)
//        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
    }

    private fun onItemClick(tourCardModel: TourCardModel) {
        findNavController().navigate(R.id.fragment_tour_ditails)
    }

    private fun onLikeClick(tourCardModel: TourCardModel, position: Int) {
        listTour[position].isLiked = !tourCardModel.isLiked
    }
}
