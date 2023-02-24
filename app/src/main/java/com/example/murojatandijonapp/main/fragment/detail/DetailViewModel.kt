package com.example.murojatandijonapp.main.fragment.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.use_case.base.AllUseCase
import com.example.domain.util.ResponseL
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val useCase: AllUseCase
): ViewModel(){
    private val _state: MutableStateFlow<DetailState> = MutableStateFlow(DetailState.Idle)
    val state: StateFlow<DetailState> get() = _state


    init {
        getAllPerson()
    }

    private fun getAllPerson(){
        viewModelScope.launch {
            useCase.getAllPersonUseCase(Unit).collect{response->
                when(response){
                    is ResponseL.Loading -> _state.update {
                        DetailState.Loading
                    }
                    is ResponseL.Error -> _state.update {
                        DetailState.Error(response.message)
                    }
                    is ResponseL.Success -> {
                        _state.update {
                            DetailState.Success
                        }
                    }
                }
            }
        }
    }

}