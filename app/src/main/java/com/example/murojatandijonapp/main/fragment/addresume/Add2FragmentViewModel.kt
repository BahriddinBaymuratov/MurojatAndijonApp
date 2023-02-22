package com.example.murojatandijonapp.main.fragment.addresume

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Person
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
class Add2FragmentViewModel @Inject constructor(
    private val useCase: AllUseCase
) : ViewModel() {
    private val _state: MutableStateFlow<Add2FragmentState> =
        MutableStateFlow(Add2FragmentState.Idle)
    val state: StateFlow<Add2FragmentState> get() = _state

    fun addResume(person: Person) {
        viewModelScope.launch {
            useCase.createPersonUseCase(person).collect { response ->
                when (response) {
                    is ResponseL.Loading -> {
                        _state.update {
                            Add2FragmentState.Loading
                        }
                        delay(1000L)
                    }
                    is ResponseL.Error -> _state.update {
                        Add2FragmentState.Error(response.message)
                    }
                    is ResponseL.Success -> _state.update {
                        Add2FragmentState.Success
                    }
                }
            }
        }
    }
}