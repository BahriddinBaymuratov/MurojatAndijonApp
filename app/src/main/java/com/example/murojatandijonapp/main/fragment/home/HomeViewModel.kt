package com.example.murojatandijonapp.main.fragment.home

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
class HomeViewModel @Inject constructor(
    private val useCase: AllUseCase
): ViewModel(){
    private val _state: MutableStateFlow<HomeState> = MutableStateFlow(HomeState.Idle)
    val state: StateFlow<HomeState> get() = _state

    init {
        getAllPerson("")
    }

    private fun getAllPerson(userId:String){
        viewModelScope.launch {
            useCase.getAllPersonUseCase(userId).collect{response->
                when(response){
                    is ResponseL.Loading -> _state.update {
                        HomeState.Loading
                    }
                    is ResponseL.Error -> _state.update {
                        HomeState.Error(response.message)
                    }
                    is ResponseL.Success -> {
                        _state.update {
                            HomeState.Success(response.data)
                        }
                    }
                }
            }
        }
    }
}