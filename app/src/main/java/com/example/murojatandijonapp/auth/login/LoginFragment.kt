package com.example.murojatandijonapp.auth.login

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.domain.model.User
import com.example.domain.util.viewBinding
import com.example.murojatandijonapp.R
import com.example.murojatandijonapp.base.BaseFragment
import com.example.murojatandijonapp.databinding.FragmentLoginBinding
import kotlinx.coroutines.flow.collect

class LoginFragment : BaseFragment(R.layout.fragment_login) {
    private val binding by viewBinding { FragmentLoginBinding.bind(it) }
    private val viewModel: LoginViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnRegister.click {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
        binding.btnLogin.click {
            val email = binding.email.text.toString().trim()
            val password = binding.password.text.toString().trim()
            if (validate(email, password)){
                viewModel.login(User("",email,password))
            }else{
                snackBar("Email Password kiriting !!")
            }
        }
        observeViewModel()



    }

    private fun observeViewModel() {
        lifecycleScope.launchWhenResumed {
            viewModel.state.collect { state ->
                when(state){
                    is LoginState.Idle -> Unit
                    is LoginState.Loading -> {
                        binding.prg.isVisible = true
                        binding.btnLogin.isVisible = false
                    }
                    is LoginState.Error -> {
                        binding.prg.isVisible = false
                        binding.btnLogin.isVisible = true
                        snackBar(state.message)
                        print("@@@@${state.message}")
                    }
                    is LoginState.Success -> {
                        binding.btnLogin.isVisible = true
                        binding.prg.isVisible = false
                        snackBar("Successfully logged in ")
                        startActivity()
                    }
                }
            }
        }
    }
}