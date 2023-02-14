package com.example.data.di

import com.example.data.repository.AuthRepositoryImpl
import com.example.domain.repository.AuthRepository
import com.example.domain.use_case.auth_use_case.LoginUseCase
import com.example.domain.use_case.auth_use_case.RegisterUseCase
import com.example.domain.use_case.base.AllUseCase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class )
class AppModule {

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth{
        return FirebaseAuth.getInstance()
    }
    @Provides
    @Singleton
    fun provideFirebaseFireStore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }
    @Provides
    @Singleton
    fun provideAuthRepository(
        auth: FirebaseAuth,
        fireStore: FirebaseFirestore
    ): AuthRepository {
        return AuthRepositoryImpl(auth, fireStore)
    }
    @Provides
    @Singleton
    fun provideAllUseCases(
        authRepository: AuthRepository,
//        productRepository: ProductRepository
    ): AllUseCase {
        return AllUseCase(
            loginUseCase = LoginUseCase(authRepository),
            registerUseCase = RegisterUseCase(authRepository),
//            logOutUseCase = LogOutUseCase(authRepository),
        )
    }



}