package com.example.domain.use_case.person_use_case

import com.example.domain.model.Person
import com.example.domain.repository.PersonRepository
import com.example.domain.use_case.base.BaseUseCase
import com.example.domain.util.ResponseL
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

typealias GetAllPersonBaseUseCase = BaseUseCase<Unit, Flow<ResponseL<List<Person>>>>

class GetAllPersonUseCase @Inject constructor(
    private val repository: PersonRepository
): GetAllPersonBaseUseCase{
    override suspend fun invoke(params: Unit): Flow<ResponseL<List<Person>>> {
        return repository.getAllPerson()
    }
}