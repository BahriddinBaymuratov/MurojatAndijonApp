package com.example.murojatandijonapp.main.fragment.addresume

sealed class Add2FragmentState {
    object Idle : Add2FragmentState()
    object Loading : Add2FragmentState()
    data class Error(val message: String) : Add2FragmentState()
    object Success : Add2FragmentState()
}