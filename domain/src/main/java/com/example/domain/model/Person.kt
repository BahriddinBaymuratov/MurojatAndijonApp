package com.example.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Person(
    val name: String = "",
    val lastName: String = "",
    val young: String = "",
    val street: String = "",
    val address: String = "",
    val number: String = "",
    val gender: String = "",
    val description: String = "",
    val image: String = "",
) : Parcelable
