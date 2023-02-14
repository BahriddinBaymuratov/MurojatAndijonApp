package com.example.domain.use_case.auth_use_case

import com.example.domain.model.User
import com.example.domain.repository.AuthRepository
import com.example.domain.use_case.base.BaseUseCase
import com.example.domain.util.ResponseL
import kotlinx.coroutines.flow.Flow

typealias RegisterBaseUseCase = BaseUseCase<User, Flow<ResponseL<Boolean>>>

class RegisterUseCase(
    private val auth: AuthRepository
) : RegisterBaseUseCase {
    override suspend fun invoke(params: User): Flow<ResponseL<Boolean>> {
        return auth.register(params)
    }
}