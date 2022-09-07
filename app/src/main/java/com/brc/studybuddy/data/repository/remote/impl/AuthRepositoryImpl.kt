package com.brc.studybuddy.data.repository.remote.impl

import com.brc.studybuddy.data.model.Token
import com.brc.studybuddy.data.model.UserInput
import com.brc.studybuddy.data.repository.AuthRepository
import com.brc.studybuddy.data.repository.remote.endpoints.AuthApi

class AuthRepositoryImpl(
    private val authApi: AuthApi
): AuthRepository {

    override suspend fun authenticate(userInput: UserInput): Token =
        authApi.authenticate(userInput = userInput).data!!

    override suspend fun refresh(refreshToken: String): Token =
        authApi.refresh(refreshToken = refreshToken).data!!

}