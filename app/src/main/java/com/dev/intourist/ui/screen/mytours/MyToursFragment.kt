package com.dev.intourist.ui.screen.mytours

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.dev.intourist.R
import com.dev.intourist.databinding.FragmentMyToursBinding
import com.dev.intourist.presentation.base.fragment.BaseFragment
import com.dev.intourist.ui.screen.home.adapters.CategoriesAdapter
import com.dev.intourist.ui.screen.mytours.adapter.MyTourModel
import com.dev.intourist.ui.screen.mytours.adapter.MyToursAdapter

class MyToursFragment : BaseFragment<FragmentMyToursBinding, MyToursViewModel>(R.layout.fragment_my_tours) {
//    private lateinit var binding: FragmentMyToursBinding
    override val binding: FragmentMyToursBinding by viewBinding(FragmentMyToursBinding::bind)
    override val viewModel: MyToursViewModel by viewModel()

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

//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        binding = FragmentMyToursBinding.inflate(inflater, container, false)
//        return binding.root
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            btnSearchTours.setOnClickListener {
                findNavController().navigate(R.id.fragment_search)
            }
            rvCategories.adapter = CategoriesAdapter(this@MyToursFragment::onClickCategory, listCategories, requireContext())
            updateCategoryAdapter(category)
        }
    }

    private fun onDelete(position: Int) {
        val builder = AlertDialog.Builder(requireContext())
        builder
            .setMessage("Вы уверены, что хотите удалить этот тур?")
            .setNegativeButton("Отменить") { dialog, i ->
                dialog.dismiss()
            }
            .setPositiveButton("Удалить") { dialog, i ->
                if (list.isNotEmpty()) {
                    list.removeAt(position)
                    binding.rvMyTours.adapter?.notifyItemRemoved(position)
                    updateCategoryAdapter(category)
                }
            }
            .show()
    }

    private fun onClickTour() {
        findNavController().navigate(R.id.myTourDetailsFragment)
    }

    private fun onClickCategory(category: String) {
        this.category = category
        updateCategoryAdapter(category)
    }

    private fun updateCategoryAdapter(category: String) {
        binding.apply {
            when (category) {
                "Предстоящие" -> {
                    if (list.isNotEmpty()) {
                        rvMyTours.adapter = MyToursAdapter(list, this@MyToursFragment::onClickTour, this@MyToursFragment::onDelete)
                    } else {
                        imageContainer.visibility = View.VISIBLE
                        tvNoToursFound.text = resources.getString(R.string.no_my_tours_future)
                    }
                }

                "Прошедшие" -> {
                    if (list.isNotEmpty()) {
                        rvMyTours.adapter = MyToursAdapter(list, this@MyToursFragment::onClickTour, this@MyToursFragment::onDelete)
                    } else {
                        imageContainer.visibility = View.VISIBLE
                        tvNoToursFound.text = resources.getString(R.string.no_my_tours_past)
                    }
                }

                "Индивидуальный тур" -> {
                    if (list.isNotEmpty()) {
                        rvMyTours.adapter = MyToursAdapter(list, this@MyToursFragment::onClickTour, this@MyToursFragment::onDelete)
                    } else {
                        imageContainer.visibility = View.GONE
                        tvNoToursFound.text = resources.getString(R.string.no_my_tours_past)
                    }
                }
            }
        }
    }
}
