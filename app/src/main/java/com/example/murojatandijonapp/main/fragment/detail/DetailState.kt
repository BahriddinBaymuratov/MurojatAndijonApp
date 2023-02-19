package com.example.murojatandijonapp.main.fragment.detail

import com.example.domain.model.Person

sealed class DetailState {
    object Idle: DetailState()
    object Loading : DetailState()
    data class Error(val message: String): DetailState()
    data class Success(val list: List<Person>): DetailState()
}