package com.dev.intourist.ui.screen.tour_details

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.dev.intourist.R
import com.dev.intourist.data.remote.dtos.tours.ToursModel
import com.dev.intourist.databinding.FragmentTourDetailsBinding
import com.dev.intourist.presentation.base.fragment.BaseFragment
import com.dev.intourist.ui.screen.buy.BottomSheetFragment
import com.dev.intourist.ui.screen.home.HomeFragment.Companion.TOUR_ID
import com.dev.intourist.ui.screen.home.adapters.categories.CategoriesAdapter
import com.dev.intourist.ui.screen.home.adapters.tour_card.TourCardAdapter
import com.dev.intourist.ui.screen.home.adapters.vp.VPAdapter
import com.dev.intourist.ui.screen.tour_details.adapter.equipment.EquipmentAdapter
import com.dev.intourist.ui.screen.tour_details.adapter.includes.IncludesAdapter
import com.dev.intourist.ui.screen.tour_details.adapter.notincluded.NotIncludedAdapter
import com.dev.intourist.ui.screen.tour_details.adapter.pickuplocetions.DetailAdapter
import com.dev.intourist.ui.screen.tour_details.adapter.program.ProgramAdapter
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.StateFlow
import com.dev.intourist.common.UIState

class TourDetailsFragment :
    BaseFragment<FragmentTourDetailsBinding, TourDetailsViewModel>(R.layout.fragment_tour_details) {

    override val binding: FragmentTourDetailsBinding by viewBinding(FragmentTourDetailsBinding::bind)
    override val viewModel: TourDetailsViewModel by viewModel()
    lateinit var adapter: TourCardAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter =
            TourCardAdapter(
                requireContext(),
                true,
                this@TourDetailsFragment::onClickTour,
                this@TourDetailsFragment::onLikeClick,
            )
        binding.apply {
            rvToursRecomindation.adapter = adapter
        }
        var whatsAppNumber = "996704848277"
        var telegrammName = "medetbekov002"
        var id = -1
        arguments?.let {
            id = it.getInt(TOUR_ID)
        }
        CoroutineScope(Dispatchers.Main).launch {
            viewModel.getContacts().stateHandler(
                success = {
                    whatsAppNumber = it.results[0].whatsapp_link
                    telegrammName = it.results[0].telegram_link
                    Log.e("ololo", "contacts: ${it.results}")
                }
            )
            if (id != -1) {
                viewModel.getTourById(id).stateHandler(
                    success = {
                        showData(it)
                    }
                )
            }
            viewModel.getAllTours(1).stateHandler(
                success = {
                    Log.e("ololo", "Success: ${it}")
                    adapter.reloadData(it.results)
                }
            )
        }
        binding.apply {
            btnBuy.setOnClickListener {
                findNavController().navigate(R.id.byCardFragment)
            }
            btnOrderIndividualTour.setOnClickListener {
                BottomSheetFragment().show(childFragmentManager, "buy tour tag")
            }
            btnContactWhatsApp.setOnClickListener {
                openWhatsApp(whatsAppNumber)
            }
            btnContactTelegram.setOnClickListener {
                openTelegram(telegrammName)
            }
        }
    }

    private fun showData(it: ToursModel.Result) {
        binding.run {
            rvDates.adapter = CategoriesAdapter(this::onClickDate, it.tour_dates.map { it.date }, requireContext())
            tvRegion.text = it.region_country
            tvTourPrice.text = it.price
            tvTourTitle.text = it.title
            tvTourDesc.text = it.description
            tvTourDuration.text = it.duration.title
            tvTourDates.text = it.tour_dates[0].date

            rvRallyPoint.adapter =
                DetailAdapter(it.pickup_locations, R.drawable.ic_location)
            rvTourProgram.adapter = ProgramAdapter(it.program)
            rvIncludedInPrice.adapter =
                IncludesAdapter(it.included, R.drawable.ic_check)
            rvEquipment.adapter =
                EquipmentAdapter(it.equipment, R.drawable.ic_check)
            viewPager.adapter = VPAdapter(it.images)
            indicator.setViewPager(viewPager)
            rvNotIncluded.adapter =
                NotIncludedAdapter(it.not_included, R.drawable.ic_cancel)
        }
    }

    private fun onLikeClick(tourCardModel: ToursModel.Result) {
        // listTour[position].isLiked = !tourCardModel.isLiked
    }

    private fun onClickTour(tour: ToursModel.Result) {
        findNavController().navigate(R.id.fragment_tour_ditails, bundleOf(TOUR_ID to tour.id))
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
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("market://details?id=com.whatsapp")
                    )
                )
            } catch (e: ActivityNotFoundException) {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://play.google.com/store/apps/details?id=com.whatsapp")
                    )
                )
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
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("market://details?id=org.telegram.messenger")
                    )
                )
            } catch (e: ActivityNotFoundException) {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://play.google.com/store/apps/details?id=org.telegram.messenger")
                    )
                )
            }
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        val latLng = LatLng(42.8700, 74.5900)
        googleMap.addMarker(MarkerOptions().position(latLng).title("Bishkek"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng))
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(10f))
    }
}

private fun FragmentTourDetailsBinding.onClickDate(date: String) {
    // Handle date click
}

