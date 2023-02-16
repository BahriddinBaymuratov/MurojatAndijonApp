package com.example.murojatandijonapp.main.fragment

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.example.domain.util.viewBinding
import com.example.murojatandijonapp.R
import com.example.murojatandijonapp.base.BaseFragment
import com.example.murojatandijonapp.databinding.FragmentStaticBinding
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.*


class StaticFragment : BaseFragment(R.layout.fragment_static) {
    private val binding by viewBinding { FragmentStaticBinding.bind(it) }
    private val list = listOf("Jarayonda","Tugatildi")
    private lateinit var autoCompleteList: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        autoComplete()

        binding.apply {
            btnFilter.setOnClickListener {
                binding.btnFilter.isVisible = false
                binding.linearTop.isVisible = true
            }
            btnBack.setOnClickListener {
                findNavController().popBackStack()
            }
            etFromDate.setOnFocusChangeListener { _, focus ->
                if (focus){
                    showDatePicker()
                }
            }
            etToDate.setOnFocusChangeListener { _, focus ->
                if (focus){
                    showDatePicker()
                }
            }

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
        binding.autoComplete.setOnItemClickListener { adapterView, view, pos, l ->
            autoCompleteList = list[pos]
        }
    }

}