package com.example.murojatandijonapp.main.fragment.statics

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.domain.model.Person
import com.example.domain.util.viewBinding
import com.example.murojatandijonapp.R
import com.example.murojatandijonapp.base.BaseFragment
import com.example.murojatandijonapp.databinding.FragmentStaticBinding
import com.example.murojatandijonapp.main.fragment.PersonAdapter
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.*

class StaticFragment : BaseFragment(R.layout.fragment_static) {
    private val binding by viewBinding { FragmentStaticBinding.bind(it) }
    private val list = listOf("Jarayonda", "Tugatildi")
    private val fireStore by lazy { FirebaseFirestore.getInstance() }
    private val adapter1 by lazy { PersonAdapter() }
    private lateinit var autoCompleteList: String
    private var problemList: MutableList<Person> = mutableListOf()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnFilter.isVisible = true
        binding.linearTop.isVisible = false
        initViews()
    }

    private fun initViews() {
        binding.apply {
            recyclerView.apply {
                adapter = adapter1
                layoutManager = StaggeredGridLayoutManager(2, LinearLayout.VERTICAL)
            }
            btnFilter.setOnClickListener {
                binding.btnFilter.isVisible = false
                binding.linearTop.isVisible = true
                autoComplete()
            }
            btnBack.setOnClickListener {
                findNavController().popBackStack()
            }
            etFromDate.setOnFocusChangeListener { _, focus ->
                if (focus) {
                    showDatePicker()
                }
            }
            etToDate.setOnFocusChangeListener { _, focus ->
                if (focus) {
                    showDatePicker()
                }
            }

        }
        fireStore.collection("problem").get().addOnCompleteListener {
            problemList.clear()
            if (it.isSuccessful){
                val problem = it.result.toObjects(Person::class.java)
                problemList.addAll(problem)
            }
            adapter1.submitList(problemList)
            adapter1.notifyDataSetChanged()
        }
    }

    private fun showDatePicker() {
        val datePicker = MaterialDatePicker.Builder.dateRangePicker()
            .setTitleText(getString(R.string.date_enter))
            .setNegativeButtonText(getString(R.string.cancel))
            .setPositiveButtonText(getString(R.string.set))
            .build()

        datePicker.addOnPositiveButtonClickListener {
            val timeZoneUTC: TimeZone = TimeZone.getDefault()
            val offsetUTC: Int = timeZoneUTC.getOffset(Date().time) * -1
            val simpleFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
            val date1 = Date(it.first + offsetUTC)
            val date2 = Date(it.second + offsetUTC)
            binding.etFromDate.setText(simpleFormat.format(date1))
            binding.etToDate.setText(simpleFormat.format(date2))
        }
        datePicker.show(childFragmentManager, "Date_range")
    }

    private fun autoComplete() {
        val adapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, list)
        binding.autoComplete.setAdapter(adapter)
        binding.autoComplete.setOnItemClickListener { _, _, pos, _ ->
            autoCompleteList = list[pos]
        }
    }

}