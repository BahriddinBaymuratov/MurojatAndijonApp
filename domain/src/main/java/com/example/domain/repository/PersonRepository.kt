package com.example.domain.repository

import com.example.domain.model.Person
import com.example.domain.util.ResponseL
import kotlinx.coroutines.flow.Flow

interface PersonRepository {
    suspend fun savePerson(person: Person): Flow<ResponseL<Boolean>>
    suspend fun createProblem(person: Person): Flow<ResponseL<Boolean>>
    suspend fun getAllPerson(): Flow<ResponseL<List<Person>>>
}
