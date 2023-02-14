package com.example.domain.use_case.base

import com.example.domain.use_case.auth_use_case.LoginUseCase
import com.example.domain.use_case.auth_use_case.RegisterUseCase

data class AllUseCase(
    val loginUseCase: LoginUseCase,
    val registerUseCase: RegisterUseCase,
)