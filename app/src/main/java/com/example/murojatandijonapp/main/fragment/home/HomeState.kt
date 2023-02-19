package com.example.murojatandijonapp.main.fragment.home

import com.example.domain.model.Person

sealed class HomeState {
    object Idle: HomeState()
    object Loading : HomeState()
    data class Error(val message: String): HomeState()
    data class Success(val list: List<Person>): HomeState()
}