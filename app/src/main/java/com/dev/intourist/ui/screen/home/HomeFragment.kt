package com.dev.intourist.ui.screen.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.dev.intourist.R
import com.dev.intourist.common.UIState
import com.dev.intourist.data.remote.dtos.tours.ToursModel
import com.dev.intourist.databinding.FragmentHomeBinding
import com.dev.intourist.presentation.base.fragment.BaseFragment
import com.dev.intourist.ui.screen.home.adapters.categories.CategoriesAdapter
import com.dev.intourist.ui.screen.home.adapters.promocode.PromocodeAdapter
import com.dev.intourist.ui.model.promocode_details.PromocodeDetailsModel
import com.dev.intourist.ui.screen.home.adapters.tour_card.TourCardAdapter
import com.dev.intourist.ui.model.tour_card.TourCardModel
import com.dev.intourist.ui.screen.filters.FiltersFragment
import com.google.android.gms.maps.GoogleMap
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(R.layout.fragment_home) {

    override val binding: FragmentHomeBinding by viewBinding(FragmentHomeBinding::bind)
    override val viewModel: HomeViewModel by viewModel()

    private val listPromo = listOf(
        PromocodeDetailsModel(
            "Получите 10% скидку",
            "На любой тур как новый пользователь нашего приложения."
        ),
        PromocodeDetailsModel(
            "Получите 10% скидку",
            "На любой тур как новый пользователь нашего приложения."
        ),
        PromocodeDetailsModel(
            "Получите 10% скидку",
            "На любой тур как новый пользователь нашего приложения."
        ),
        PromocodeDetailsModel(
            "Получите 10% скидку",
            "На любой тур как новый пользователь нашего приложения."
        ),
    )
    private val listCategories = listOf(
        "\uD83D\uDD25 Все",
        "\uD83D\uDCA7 Водопады",
        "\uD83D\uDDFB Каньоны",
        "\uD83C\uDFD4\uFE0F Ущелья",
        "\uD83C\uDF0A Озёра",
        "\uD83D\uDEA3\uD83C\uDFFB\u200D♀\uFE0F Развлекательные",
        "⛰\uFE0F Восхождения"
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            // filter
            ivFilters.setOnClickListener {
                FiltersFragment().show(childFragmentManager, "buy tour tag")
                //findNavController().navigate(R.id.fragment_filters)
            }
            // search
            svSearchTours.setOnClickListener {
                findNavController().navigate(R.id.fragment_search)
            }
        }
        CoroutineScope(Dispatchers.Main).launch {
            viewModel.getAllTours().stateHandler(
                success = {
                    Log.e("ololo", "Success: ${it}")
                    val adapter =
                        TourCardAdapter(
                            requireContext(),
                            false,
                            this@HomeFragment::onClickTour,
                            this@HomeFragment::onLikeClick,
                            it.results
                        )
                    binding.apply {
                        rvTours.adapter = adapter
                    }
                },
                state = { state ->
                    binding.apply {
                        progressBar.isVisible = state is UIState.Loading
                    }
                }
            )
        }

        val adapterPromo = PromocodeAdapter(this::onClickPromo, listPromo)
        binding.apply {
            rvPromocode.adapter = adapterPromo
        }


        val adapterCategories =
            CategoriesAdapter(this::onClickCategory, listCategories, requireContext())
        binding.apply {
            rvCategories.adapter = adapterCategories
        }

    }

    override fun onMapReady(googleMap: GoogleMap) {
    }

    private fun onLikeClick(tour: ToursModel.Result) {
        //listTour[position].isLiked = !tourCardModel.isLiked
    }

    private fun onClickCategory(category: String) {
        //update recycler view with category
    }

    private fun onClickTour(tour: ToursModel.Result) {
        findNavController().navigate(R.id.fragment_tour_ditails, bundleOf(TOUR_ID to tour.id))
    }

    private fun onClickPromo(promocode: PromocodeDetailsModel) {
        findNavController().navigate(R.id.fragment_promocode)
    }
    companion object {
        const val   TOUR_ID = "tour_id"
    }
}
