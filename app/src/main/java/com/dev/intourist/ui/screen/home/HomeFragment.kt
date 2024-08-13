package com.dev.intourist.ui.screen.home

import android.nfc.tech.MifareUltralight.PAGE_SIZE
import android.os.Build.VERSION_CODES.R
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
    private var pageCount = 1
    lateinit var adapter: TourCardAdapter

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

        adapter = TourCardAdapter(
            requireContext(),
            false,
            this@HomeFragment::onClickTour,
            this@HomeFragment::onLikeClick
        )

       /* viewModel.tours.spectateUiState(
            success = {
                adapter.addData(it.results)
            },
            loading = { state ->
                binding.progressBar.isVisible = state is UIState.Loading
            })
*/
        val adapterPromo = PromocodeAdapter(this::onClickPromo, listPromo)
        binding.apply {
            rvPromocode.adapter = adapterPromo
        }
        val adapterCategories =
            CategoriesAdapter(this::onClickCategory, listCategories, requireContext())
        binding.apply {
            rvCategories.adapter = adapterCategories
        }

      //  requestTours()

        binding.run {
            rvTours.adapter = adapter
            // filter
            ivFilters.setOnClickListener {
                //FiltersFragment().show(childFragmentManager, "buy tour tag")
                findNavController().navigate(R.id.fragment_filters)
            }
            // search
            svSearchTours.setOnClickListener {
                findNavController().navigate(R.id.fragment_search)
            }
            // paging
            rvTours.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                    val visibleItemCount = layoutManager.childCount
                    val totalItemCount = layoutManager.itemCount
                    val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                    if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                        && firstVisibleItemPosition >= 0
                        && totalItemCount >= PAGE_SIZE
                    ) {
                        pageCount++
                      //  addTours()
                    }

                }
            })
        }
    }
/*

    private fun requestTours() {
        CoroutineScope(Dispatchers.Main).launch {
            viewModel.getAllTours(pageSize = pageCount)
        }
    }

    private fun addTours() {
        CoroutineScope(Dispatchers.Main).launch {
            viewModel.getAllTours(pageSize = pageCount)
        }
    }
*/

    override fun onMapReady(googleMap: GoogleMap) {
    }

    private fun onLikeClick(tour: ToursModel.Result) {
        //listTour[position].isLiked = !tourCardModel.isLiked
    }

    private fun onClickCategory(category: String) {
        //update recycler view with category
        if (category != "\uD83D\uDD25 Все") {
            binding.run {
                rvPromocode.visibility = View.GONE
            }
        } else {
            binding.run {
                rvPromocode.visibility = View.VISIBLE
            }
        }
    }

    private fun onClickTour(tour: ToursModel.Result) {
        findNavController().navigate(R.id.fragment_tour_ditails, bundleOf(TOUR_ID to tour.id))
    }

    private fun onClickPromo(promocode: PromocodeDetailsModel) {
        findNavController().navigate(R.id.fragment_promocode)
    }

    companion object {
        const val TOUR_ID = "tour_id"
    }

}
