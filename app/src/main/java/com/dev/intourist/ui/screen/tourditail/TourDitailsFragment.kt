package com.dev.intourist.ui.screen.tourditail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dev.intourist.R
import com.dev.intourist.databinding.FragmentTourDitailsBinding
import com.dev.intourist.ui.screen.home.adapters.VPAdapter


class TourDitailsFragment : Fragment() {

    private lateinit var binding: FragmentTourDitailsBinding

    private val listRallyPoint = listOf(
        "1-ая точка сбора: Токтогула / Шопокова, Народный",
        "2-ая точка сбора: остановка в 10 м. \n" +
                "в микрорайоне, Советская / Магистраль",
    )
    private val listTime = listOf("6:00", "6:15", "6:25", "7:30-12:00")
    private val listDesc = listOf(
        "сбор сбор Шопокова 101/1 (парковка Халык банка)",
        "отъезд (опаздывающих не ждём)",
        "отъезд 10 мкр (опаздывающих не ждём)",
        "дорога до Хижины Рацека"
    )

    private val listEquipment = listOf(
        "запасная обувь (можно оставить в бусе, по приходу переобуться)",
        "солнцезащитные очки",
        "крем",
        "запасные носки, 2-3 пары",
        "треккинговая или спортивная тёплая обувь (ботинки)",
    )

    private val listIncludeds = listOf(
        "Услуги гидов",
        "Трансфер",
        "Въезд в национальный парк",
    )
    private val list1 = listOf(
        R.drawable.image_view_pager,
        R.drawable.image_view_pager,
        R.drawable.image_view_pager,
        R.drawable.image_view_pager,
        R.drawable.image_view_pager,
        R.drawable.image_view_pager,
        R.drawable.image_view_pager,
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTourDitailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapterRallyPoint = DitailAdapter(listRallyPoint, R.drawable.ic_location_orange)
        binding.rvRallyPoint.adapter = adapterRallyPoint

        val adapterProgram = ProgramAdapter(listTime, listDesc)
        binding.rvTourProgram.adapter = adapterProgram

        val adapterIncluded = DitailAdapter(listIncludeds, R.drawable.ic_check)
        binding.rvIncludedInPrice.adapter = adapterIncluded

        val adapterEquipment = DitailAdapter(listEquipment, R.drawable.ic_check)
        binding.rvEquipment.adapter = adapterEquipment

        binding.viewPager.adapter = VPAdapter(list1)
        binding.indicator.setViewPager(binding.viewPager)
    }

}