package com.example.murojatandijonapp.main.fragment.add

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.domain.model.Person
import com.example.murojatandijonapp.R
import com.example.murojatandijonapp.base.BaseFragment
import com.example.murojatandijonapp.databinding.FragmentAddBinding
import com.example.murojatandijonapp.main.fragment.PersonAdapter
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AddFragment : BaseFragment(R.layout.fragment_add) {
    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AddFragmentViewModel by viewModels()
    private var person: Person? = null
//    private val uid by lazy { FirebaseAuth.getInstance().currentUser?.uid }

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
        binding.btnBack.click {
            findNavController().popBackStack()
        }
        binding.apply {
                val streetName = streetName.text.toString().trim()
                val address = address.text.toString().trim()
                val name = name.text.toString().trim()
                val lastName = lastName.text.toString().trim()
                val young = young.text.toString().trim()
                val gender = gender.text.toString().trim()
                val number = number.text.toString().trim()

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
                btnResume.click {
                    val bundle = bundleOf("person" to person)
                    findNavController().navigate(R.id.action_addFragment_to_add2Fragment, bundle)
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
                        snackBar("Malumotlar qabul qilindi !")
                    }
                }
            }
        }
    }
}