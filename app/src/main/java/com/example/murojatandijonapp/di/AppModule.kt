package com.example.murojatandijonapp.di

import com.example.data.repository.AuthRepositoryImpl
import com.example.data.repository.PersonRepositoryImpl
import com.example.domain.repository.AuthRepository
import com.example.domain.repository.PersonRepository
import com.example.domain.use_case.auth_use_case.LoginUseCase
import com.example.domain.use_case.auth_use_case.RegisterUseCase
import com.example.domain.use_case.base.AllUseCase
import com.example.domain.use_case.person_use_case.CreatePersonUseCase
import com.example.domain.use_case.person_use_case.GetAllPersonUseCase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Singleton
    @Provides
    fun provideFirebaseFireStore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    @Singleton
    @Provides
    fun provideAuthRepository(
        auth: FirebaseAuth,
        fireStore: FirebaseFirestore
    ): AuthRepository {
        return AuthRepositoryImpl(auth, fireStore)
    }

    @Singleton
    @Provides
    fun providePersonRepository(
        fireStore: FirebaseFirestore,
        auth: FirebaseAuth
    ): PersonRepository {
        return PersonRepositoryImpl(fireStore, auth)
    }

    @Singleton
    @Provides
    fun provideAllUseCases(
        authRepository: AuthRepository,
        repository: PersonRepository
    ): AllUseCase {
        return AllUseCase(
            loginUseCase = LoginUseCase(authRepository),
            registerUseCase = RegisterUseCase(authRepository),
            createPersonUseCase = CreatePersonUseCase(repository),
            getAllPersonUseCase = GetAllPersonUseCase(repository),
//            logOutUseCase = LogOutUseCase(authRepository),
        )
    }


}