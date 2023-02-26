package com.example.murojatandijonapp.main.fragment.add

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.domain.model.Person
import com.example.murojatandijonapp.R
import com.example.murojatandijonapp.base.BaseFragment
import com.example.murojatandijonapp.databinding.FragmentAddBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AddFragment : BaseFragment(R.layout.fragment_add) {
    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!
    private val gender = listOf("Erkak", "Ayol")
    private lateinit var autoCompleteList: String
    private val viewModel: AddFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        autoComplete()
        binding.checkbox.setOnCheckedChangeListener { _, isChecked ->
            binding.checkboxOpen.isVisible = isChecked
        }

        binding.btnBack.click {
            findNavController().popBackStack()
        }
        binding.apply {
            binding.btnResume.click {
                val streetName = streetName.text.toString().trim()
                val address = address.text.toString().trim()
                val name = name.text.toString().trim()
                val lastName = lastName.text.toString().trim()
                val young = young.text.toString().trim()
                val gender = gender.text.toString().trim()
                val number = number.text.toString().trim()

                if (name.isNotBlank() && lastName.isNotBlank() && gender.isNotBlank() && address.isNotBlank() && number.isNotBlank() && ::autoCompleteList.isInitialized) {
                    val person = Person(
                        name,
                        lastName,
                        young,
                        streetName,
                        address,
                        number,
                        gender,
                        "${R.string.m}",
                        ""
                    )
                    val bundle = bundleOf("person" to person)
                    findNavController().navigate(R.id.action_addFragment_to_add2Fragment, bundle)
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Ma'lumotlarni to'liq kiriting !!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
        observe()
    }

    private fun observe() {
        lifecycleScope.launchWhenResumed {
            viewModel.state.collect { state ->
                when (state) {
                    is AddFragmentState.Idle -> Unit
                    is AddFragmentState.Loading -> Unit
                    is AddFragmentState.Error -> {
                        snackBar(state.message)
                    }
                    is AddFragmentState.Success -> {
                        snackBar("viewModel state Successga tushdi !")
                        Log.d("@@@", "AddFragment: Malumotlar jonatildi Succesda")
                    }
                }
            }
        }
    }

    private fun autoComplete() {
        val adapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, gender)
        binding.gender.setAdapter(adapter)
        binding.gender.setOnItemClickListener { adapterView, view, pos, l ->
            autoCompleteList = gender[pos]
        }
    }
}