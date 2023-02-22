package com.example.data.repository

import com.example.domain.model.Person
import com.example.domain.repository.PersonRepository
import com.example.domain.util.ResponseL
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class PersonRepositoryImpl @Inject constructor(
    private val fireStore: FirebaseFirestore,
    auth: FirebaseAuth
) : PersonRepository {
    private var isSuccessful = false

    override suspend fun savePerson(person: Person): Flow<ResponseL<Boolean>> = flow {
        isSuccessful = false
        emit(ResponseL.Loading)
        try {
            val productId = fireStore.collection("muammolar").document().id
            val newProduct = Person(
                name = person.name, lastName = person.lastName,
                young = person.young, street = person.street,
                address = person.address, number = person.number,
                gender = person.gender, description = person.description, ""
            )
            fireStore.collection("muammolar").document(productId).set(newProduct)
                .addOnSuccessListener {
                    isSuccessful = true
                }.await()
            emit(ResponseL.Success(isSuccessful))
        } catch (e: Exception) {
            emit(ResponseL.Error(e.message.toString()))
        }
    }

    override suspend fun getAllPerson(userId: String): Flow<ResponseL<List<Person>>> =
        callbackFlow {
            ResponseL.Loading
            val snap = fireStore.collection("products")
                .whereEqualTo("userId", userId)
                .addSnapshotListener { snapshot, error ->
                    val response = if (snapshot != null) {
                        val postList = snapshot.toObjects(Person::class.java)
                        ResponseL.Success(postList)
                    } else {
                        ResponseL.Error(error?.stackTraceToString()!!)
                    }
                    trySend(response).isSuccess
                }
            awaitClose {
                snap.remove()
            }
        }

    override suspend fun createProblem(person: Person): Flow<ResponseL<Boolean>> = flow {
        isSuccessful = false
        try {
            val id = fireStore.collection("muammolar").document().id
            val newProblem = Person(
                name = person.name, lastName = person.lastName,
                young = person.young, street = person.street,
                address = person.address, number = person.number,
                gender = person.gender, description = person.description,
                image = person.image
            )
            fireStore.collection("muammolar").document(id).set(newProblem).addOnSuccessListener {
                isSuccessful = true
            }.await()
            emit(ResponseL.Success(isSuccessful))
        } catch (e: Exception) {
            emit(ResponseL.Error(e.message.toString()))
        }
    }
}