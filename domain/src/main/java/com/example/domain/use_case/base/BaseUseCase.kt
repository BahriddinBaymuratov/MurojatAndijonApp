package com.example.domain.use_case.base

interface BaseUseCase<in parameter, out Result> {
    suspend operator fun invoke(params: parameter): Result
}