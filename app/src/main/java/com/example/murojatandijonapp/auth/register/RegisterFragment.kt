package com.example.murojatandijonapp.auth.register

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.domain.model.User
import com.example.domain.util.viewBinding
import com.example.murojatandijonapp.R
import com.example.murojatandijonapp.base.BaseFragment
import com.example.murojatandijonapp.databinding.FragmentRegisterBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : BaseFragment(R.layout.fragment_register) {
    private val binding by viewBinding { FragmentRegisterBinding.bind(it) }
    private val viewModel: RegisterViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolBar.click {
            findNavController().popBackStack()
        }
        binding.apply {
            binding.btnRegister.click {
                val fullName = fullName.text.toString().trim()
                val email = email.text.toString().trim()
                val password = password.text.toString().trim()

                if (validate(email, password) && fullName.isNotBlank()) {
                    viewModel.register(User(fullName, email, password))
                } else {
                    snackBar("Enter your data!!")
                }
            }
        }
        observeViewModel()
    }

    private fun observeViewModel() {
        lifecycleScope.launchWhenResumed {
            viewModel.state.collect { state ->
                when (state) {
                    is RegisterState.Idle -> Unit
                    is RegisterState.Loading -> {
                        binding.prg.isVisible = true
                        binding.btnRegister.isVisible = false
                        Log.d("@@@", "observeViewModel: viewModel Loading ")
                    }
                    is RegisterState.Error -> {
                        binding.prg.isVisible = false
                        binding.btnRegister.isVisible = true
                        snackBar(state.message)
                        Log.d("@@@", "observeViewModel: viewModel Loading ")
                    }
                    is RegisterState.Success -> {
                        binding.prg.isVisible = false
                        binding.btnRegister.isVisible = true
                        snackBar("Successfully registered")
                        Log.d("@@@", "observeViewModel: viewModel Loading ")
                        startActivity()
                    }
                }
            }
        }
    }
}