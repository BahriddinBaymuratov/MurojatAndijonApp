package com.example.domain.use_case.base

import com.example.domain.use_case.auth_use_case.LoginUseCase
import com.example.domain.use_case.auth_use_case.RegisterUseCase
import com.example.domain.use_case.person_use_case.CreatePersonUseCase
import com.example.domain.use_case.person_use_case.GetAllPersonUseCase

data class AllUseCase(
    val loginUseCase: LoginUseCase,
    val registerUseCase: RegisterUseCase,
    val createPersonUseCase: CreatePersonUseCase,
    val getAllPersonUseCase: GetAllPersonUseCase,
)