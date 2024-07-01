package com.dev.intourist.ui.screen.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.dev.intourist.R
import com.dev.intourist.databinding.FragmentHomeBinding
import com.dev.intourist.presentation.base.fragment.BaseFragment
import com.dev.intourist.ui.screen.home.adapters.CategoriesAdapter
import com.dev.intourist.ui.screen.home.adapters.PromocodeAdapter
import com.dev.intourist.ui.screen.home.adapters.PromocodeDetailsModel
import com.dev.intourist.ui.screen.home.adapters.TourCardAdapter
import com.dev.intourist.ui.screen.home.adapters.TourCardModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(R.layout.fragment_home) {

    override val binding: FragmentHomeBinding by viewBinding(FragmentHomeBinding::bind)
    override val viewModel: HomeViewModel by viewModel()
//    private val binding: FragmentHomeBinding by viewBinding()
//    private val viewModel: HomeViewModel by viewModels()

    //Пока данные мы не получаем, так что вот вам придуманные данные:
    //image_view_pager потом надо удалить из ресурсов
    private val list1 = listOf(
        R.drawable.image_view_pager,
        R.drawable.image_view_pager,
        R.drawable.image_view_pager,
        R.drawable.image_view_pager,
        R.drawable.image_view_pager,
        R.drawable.image_view_pager,
        R.drawable.image_view_pager,
    )

    private val listTour = listOf(
        TourCardModel(
            list1,
            "Ущелье Ала-Арча",
            "1900 c.",
            "1200 c.",
            "Однодневный тур",
            "15 мая, 16 мая, 17 мая, 18 мая, 19 мая, 20 мая, 21 мая",
            true
        ),
        TourCardModel(
            list1,
            "Ущелье Ала-Арча",
            "1900 c.",
            "1200 c.",
            "Однодневный тур",
            "15 мая, 16 мая, 17 мая, 18 мая, 19 мая, 20 мая, 21 мая",
            false
        ),
        TourCardModel(
            list1,
            "Ущелье Ала-Арча",
            "1900 c.",
            "1200 c.",
            "Однодневный тур",
            "15 мая, 16 мая, 17 мая, 18 мая, 19 мая, 20 мая, 21 мая",
            false
        ),
        TourCardModel(
            list1,
            "Ущелье Ала-Арча",
            "1900 c.",
            "1200 c.",
            "Однодневный тур",
            "15 мая, 16 мая, 17 мая, 18 мая, 19 мая, 20 мая, 21 мая",
            false
        ),
        TourCardModel(
            list1,
            "Ущелье Ала-Арча",
            "1900 c.",
            "1200 c.",
            "Однодневный тур",
            "15 мая, 16 мая, 17 мая, 18 мая, 19 мая, 20 мая, 21 мая",
            false
        ),
        TourCardModel(
            list1,
            "Ущелье Ала-Арча",
            "1900 c.",
            "1200 c.",
            "Однодневный тур",
            "15 мая, 16 мая, 17 мая, 18 мая, 19 мая, 20 мая, 21 мая",
            false
        ),
    )
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
                //FiltersFragment().show(childFragmentManager, "buy tour tag")
                findNavController().navigate(R.id.fragment_filters)
            }
            // search
            svSearchTours.setOnClickListener {
                findNavController().navigate(R.id.fragment_search)
            }
        }

        val adapter = TourCardAdapter(requireContext(), false, this::onClickTour, this::onLikeClick, listTour)
        binding.apply {
            rvTours.adapter = adapter
        }
//        binding.rvTours.adapter = adapter

        val adapterPromo = PromocodeAdapter(this::onClickPromo, listPromo)
        binding.apply {
            rvPromocode.adapter = adapterPromo
        }
//        binding.rvPromocode.adapter = adapterPromo

        val adapterCategories =
            CategoriesAdapter(this::onClickCategory, listCategories, requireContext())
        binding.apply {
            rvCategories.adapter = adapterCategories
        }
//        binding.rvCategories.adapter = adapterCategories

    }

    private fun onLikeClick(tourCardModel: TourCardModel, position: Int) {
        listTour[position].isLiked = !tourCardModel.isLiked
    }

    private fun onClickCategory(category: String) {
        //update recycler view with category
    }

    private fun onClickTour(tourCardModel: TourCardModel) {
        findNavController().navigate(R.id.fragment_tour_ditails)
    }

    private fun onClickPromo(promocode: PromocodeDetailsModel) {
        findNavController().navigate(R.id.fragment_promocode)
    }
}
