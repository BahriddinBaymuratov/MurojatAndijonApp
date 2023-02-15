package com.example.murojatandijonapp.auth.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.User
import com.example.domain.use_case.base.AllUseCase
import com.example.domain.util.ResponseL
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val useCase: AllUseCase
) : ViewModel() {
    private val _state: MutableStateFlow<LoginState> = MutableStateFlow(LoginState.Idle)
    val state: StateFlow<LoginState> get() = _state

    fun login(user: User) {
        viewModelScope.launch {
            useCase.loginUseCase(user).collect { response ->
                when (response) {
                    is ResponseL.Loading -> {
                        _state.update {
                            LoginState.Loading
                        }
                        delay(1000L)
                    }
                    is ResponseL.Error -> _state.update {
                        LoginState.Error(response.message)
                    }
                    is ResponseL.Success -> _state.update {
                        LoginState.Success
                    }
                }
            }
        }
    }

}