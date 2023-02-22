package com.example.domain.use_case.person_use_case

import com.example.domain.model.Person
import com.example.domain.repository.PersonRepository
import com.example.domain.use_case.base.BaseUseCase
import com.example.domain.util.ResponseL
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

typealias CreateProblemBaseUseCase = BaseUseCase<Person, Flow<ResponseL<Boolean>>>

class CreateProblemUseCase @Inject constructor(
    private val repository: PersonRepository
): CreateProblemBaseUseCase{
    override suspend fun invoke(params: Person): Flow<ResponseL<Boolean>> {
        return repository.createProblem(params)
    }
}