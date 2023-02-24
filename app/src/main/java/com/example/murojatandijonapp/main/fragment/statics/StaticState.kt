package com.example.murojatandijonapp.main.fragment.statics

sealed class StaticState {
    object Loading: StaticState()
    object Success: StaticState()
}