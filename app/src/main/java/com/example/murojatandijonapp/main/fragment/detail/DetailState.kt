package com.example.murojatandijonapp.main.fragment.detail

sealed class DetailState {
    object Idle : DetailState()
    object Loading : DetailState()
    data class Error(val message: String) : DetailState()
    object Success : DetailState()
}