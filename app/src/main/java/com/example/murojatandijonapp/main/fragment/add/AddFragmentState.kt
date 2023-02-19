package com.example.murojatandijonapp.main.fragment.add

sealed class AddFragmentState {
    object Idle : AddFragmentState()
    object Loading : AddFragmentState()
    data class Error(val message: String) : AddFragmentState()
    object Success : AddFragmentState()
}