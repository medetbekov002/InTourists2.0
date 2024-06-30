package com.dev.intourist.ui.screen.tourditail

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.dev.intourist.R
import com.dev.intourist.databinding.FragmentTourDetailsBinding
import com.dev.intourist.ui.screen.buy.BottomSheetFragment
import com.dev.intourist.ui.screen.home.adapters.TourCardAdapter
import com.dev.intourist.ui.screen.home.adapters.TourCardModel
import com.dev.intourist.ui.screen.home.adapters.VPAdapter


class TourDetailsFragment : Fragment() {

    private lateinit var binding: FragmentTourDetailsBinding

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
    private val listTour = listOf(
        TourCardModel(
            list1,
            "Ущелье Ала-Арча",
            "1900 c.",
            "1200 c.",
            "Однодневный тур",
            "15 мая, 16 мая, 17 мая, 18 мая, 19 мая, 20 мая, 21 мая",false
        ),
        TourCardModel(
            list1,
            "Ущелье Ала-Арча",
            "1900 c.",
            "1200 c.",
            "Однодневный тур",
            "15 мая, 16 мая, 17 мая, 18 мая, 19 мая, 20 мая, 21 мая",false
        ),
        TourCardModel(
            list1,
            "Ущелье Ала-Арча",
            "1900 c.",
            "1200 c.",
            "Однодневный тур",
            "15 мая, 16 мая, 17 мая, 18 мая, 19 мая, 20 мая, 21 мая",false
        ),
        TourCardModel(
            list1,
            "Ущелье Ала-Арча",
            "1900 c.",
            "1200 c.",
            "Однодневный тур",
            "15 мая, 16 мая, 17 мая, 18 мая, 19 мая, 20 мая, 21 мая",false
        ),
        TourCardModel(
            list1,
            "Ущелье Ала-Арча",
            "1900 c.",
            "1200 c.",
            "Однодневный тур",
            "15 мая, 16 мая, 17 мая, 18 мая, 19 мая, 20 мая, 21 мая",false
        ),
        TourCardModel(
            list1,
            "Ущелье Ала-Арча",
            "1900 c.",
            "1200 c.",
            "Однодневный тур",
            "15 мая, 16 мая, 17 мая, 18 мая, 19 мая, 20 мая, 21 мая",false
        ),
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTourDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnBuy.setOnClickListener {
            findNavController().navigate(R.id.byCardFragment)
        }
        binding.btnOrderIndividualTour.setOnClickListener {
            BottomSheetFragment().show(childFragmentManager, "buy tour tag")
        }

        binding.btnContactWhatsApp.setOnClickListener {
            val phoneNumber = "996704848277" // Номер телефона в международном формате без плюса
            openWhatsApp(phoneNumber)
        }
        binding.btnContactTelegram.setOnClickListener {
            val username = "medetbekov002" // Telegram username без @
            openTelegram(username)
        }
        val adapter = TourCardAdapter(requireContext(), true, this::onClickTour, this::onLikeClick,listTour)
        binding.rvToursRecomindation.adapter = adapter

        val adapterRallyPoint = DitailAdapter(listRallyPoint, R.drawable.ic_location)
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

    private fun onLikeClick(tourCardModel: TourCardModel, position: Int) {
        listTour[position].isLiked = !tourCardModel.isLiked
    }

    private fun onClickTour(tourCardModel: TourCardModel) {
        findNavController().navigate(R.id.fragment_tour_ditails)
    }

    private fun openWhatsApp(phoneNumber: String) {
        val url = "https://wa.me/$phoneNumber"

        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse(url)
            setPackage("com.whatsapp")
        }

        try {
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(
                requireContext(),
                "WhatsApp is not installed on your device",
                Toast.LENGTH_SHORT
            ).show()

            try {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.whatsapp")))
            } catch (e: ActivityNotFoundException) {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.whatsapp")))
            }
        }
    }

    private fun openTelegram(username: String) {
        val url = "tg://resolve?domain=$username"

        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse(url)
            setPackage("org.telegram.messenger")
        }

        try {
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(
                requireContext(),
                "Telegram is not installed on your device",
                Toast.LENGTH_SHORT
            ).show()

            try {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=org.telegram.messenger")))
            } catch (e: ActivityNotFoundException) {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=org.telegram.messenger")))
            }
        }
    }

}