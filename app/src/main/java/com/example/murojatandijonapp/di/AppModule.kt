package com.example.murojatandijonapp.di
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
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideFirebaseAuth(): FirebaseAuth{
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