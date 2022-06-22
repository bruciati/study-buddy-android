package com.brc.studybuddy.data.repository.remote

import com.brc.studybuddy.domain.model.Token
import com.brc.studybuddy.domain.model.UserInput
import com.brc.studybuddy.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val authApi: AuthApi
): AuthRepository {

    override suspend fun authenticate(userInput: UserInput): Token =
        authApi.authenticate(userInput = userInput).data!!


    override suspend fun refresh(refreshToken: String): Token =
        authApi.refresh(refreshToken = refreshToken).data!!

}