package com.example.domain.repository

import com.example.domain.model.User
import com.example.domain.util.ResponseL
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun login(user: User): Flow<ResponseL<Boolean>>
    suspend fun register(user: User): Flow<ResponseL<Boolean>>
    fun logOut()
}