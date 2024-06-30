package com.dev.intourist.ui.screen.mytours

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.dev.intourist.R
import com.dev.intourist.databinding.FragmentMyToursBinding
import com.dev.intourist.ui.screen.home.adapters.CategoriesAdapter
import com.dev.intourist.ui.screen.home.adapters.TourCardModel
import com.dev.intourist.ui.screen.mytours.adapter.MyTourModel
import com.dev.intourist.ui.screen.mytours.adapter.MyToursAdapter
import com.google.android.material.animation.Positioning


class MyToursFragment : Fragment() {
    private lateinit var binding: FragmentMyToursBinding
    private val listCategories = listOf(
        "Предстоящие",
        "Прошедшие",
        "Индивидуальный тур"
    )
    private var category = "Предстоящие"
    private val list = mutableListOf<MyTourModel>(
        MyTourModel(
            R.drawable.image_my_tour,
            "Ущелье Ала-Арча",
            "Однодневный",
            "15 мая, 2024",
            "1200 c.",
            "Через один день"
        ),
        MyTourModel(
            R.drawable.image_my_tour,
            "Ущелье Ала-Арча",
            "Однодневный",
            "15 мая, 2024",
            "1200 c.",
            "Через один день"
        ),
    )


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyToursBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSearchTours.setOnClickListener {
            findNavController().navigate(R.id.fragment_search)
        }
        binding.rvCategories.adapter =
            CategoriesAdapter(this::onClickCategory, listCategories, requireContext())

        updateCategoryAdapter(category)

    }

    private fun onDelete(position: Int) {
        val builder = AlertDialog.Builder(requireContext())
        builder
            .setMessage("Вы уверены, что хотите удалить этот тур?")
            .setNegativeButton("Отменить") { dialog, i ->
                dialog.dismiss()
            }
            .setPositiveButton("Удалить") { dialog, i ->
                //delete
                if (list.isNotEmpty()) {
                    list.removeAt(position)
                    binding.rvMyTours.adapter?.notifyItemRemoved(position)
                    updateCategoryAdapter(category)
                }

            }
            .show()
    }

    private fun onClickTour() {
        findNavController().navigate(R.id.myTourDitailsFragment)
    }

    private fun onClickCategory(category: String) {
        this.category = category
        updateCategoryAdapter(category)
    }

    fun updateCategoryAdapter(category: String) {
        when (category) {
            "Предстоящие" -> {
                if (list.isNotEmpty()) {
                    binding.rvMyTours.adapter =
                        MyToursAdapter(list, this::onClickTour, this::onDelete)
                } else {
                    binding.imageContainer.visibility = View.VISIBLE
                    binding.tvNoToursFound.text = resources.getString(R.string.no_my_tours_future)
                }
            }

            "Прошедшие" -> {
                if (list.isNotEmpty()) {
                    binding.rvMyTours.adapter =
                        MyToursAdapter(list, this::onClickTour, this::onDelete)
                } else {
                    binding.imageContainer.visibility = View.VISIBLE
                    binding.tvNoToursFound.text = resources.getString(R.string.no_my_tours_past)
                }
            }

            "Индивидуальный тур" -> {
                if (list.isNotEmpty()) {
                    binding.rvMyTours.adapter =
                        MyToursAdapter(list, this::onClickTour, this::onDelete)
                } else {
                    binding.imageContainer.visibility = View.GONE
                    binding.tvNoToursFound.text = resources.getString(R.string.no_my_tours_past)
                }
            }
        }
    }
}