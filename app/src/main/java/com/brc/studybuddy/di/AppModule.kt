package com.brc.studybuddy.di

import com.brc.studybuddy.data.repository.mock.AccessTokenRepositoryMock
import com.brc.studybuddy.data.repository.mock.GroupRepositoryMock
import com.brc.studybuddy.data.repository.remote.AuthApi
import com.brc.studybuddy.data.repository.remote.AuthRefresher
import com.brc.studybuddy.data.repository.remote.AuthRepositoryImpl
import com.brc.studybuddy.domain.repository.AccessTokenRepository
import com.brc.studybuddy.domain.repository.AuthRepository
import com.brc.studybuddy.domain.repository.GroupRepository
import com.brc.studybuddy.domain.use_case.groups.CreateGroup
import com.brc.studybuddy.domain.use_case.groups.GetGroups
import com.brc.studybuddy.domain.use_case.groups.GroupUseCases
import com.brc.studybuddy.domain.use_case.login.Authenticate
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

const val SERVER_BASE_URL = "http://192.168.1.230:8080/"

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    /*
    * Provides a fake datasource implementation for the Groups Repository
    */
    @Provides
    @Singleton
    fun injectGroupRepository(): GroupRepository = GroupRepositoryMock()

    /*
    * Provides a fake datasource implementation for the AccessToken Repository
    */
    @Provides
    @Singleton
    fun injectAccessTokenRepository(): AccessTokenRepository = AccessTokenRepositoryMock()

    /*
     * Wire use cases and their repository(es) dependency
     */
    @Provides
    @Singleton
    fun injectGroupUseCases(repository: GroupRepository): GroupUseCases = GroupUseCases(
        getGroups = GetGroups(repository),
        createGroup = CreateGroup(repository),
    )

    @Provides
    @Singleton
    fun injectAuthenticationUseCase(
        authRepository: AuthRepository,
        accessTokenRepository: AccessTokenRepository
    ): Authenticate = Authenticate(
        authRepository,
        accessTokenRepository
    )

    @Provides
    @Singleton
    fun injectAuthRefresher(
        authRepository: AuthRepository,
        accessTokenRepository: AccessTokenRepository
    ): AuthRefresher = AuthRefresher(
        authRepository = authRepository,
        accessTokenRepository = accessTokenRepository
    )

    /*
    * Provides a fake datasource implementation for the Authentication Repository
    */
    @Provides
    @Singleton
    fun injectAuthRepository(): AuthRepository {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(SERVER_BASE_URL)
            .client(OkHttpClient.Builder().build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return AuthRepositoryImpl(
            retrofit.create(AuthApi::class.java)
        )

    }

    @Provides
    @Singleton
    fun injectAutoRefreshRetrofitService(
        authRefresher: AuthRefresher
    ): Retrofit {
        val loggerInterceptor = HttpLoggingInterceptor()
        loggerInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val httpClient = OkHttpClient.Builder()
            .addInterceptor(loggerInterceptor)
            .addInterceptor { chain ->
                chain.proceed(chain.request().newBuilder().also {
                    it.addHeader("Accept", "application/json")
                }.build())
            }.also { client ->
                client.authenticator(authRefresher)
            }.build()

        return Retrofit.Builder()
            .baseUrl(SERVER_BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}