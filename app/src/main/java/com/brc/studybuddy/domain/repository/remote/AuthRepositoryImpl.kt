package com.brc.studybuddy.domain.repository.remote

import com.brc.studybuddy.domain.model.Token
import com.brc.studybuddy.domain.model.UserInput
import com.brc.studybuddy.domain.repository.AuthRepository
import com.brc.studybuddy.domain.repository.remote.endpoints.AuthApi


class AuthRepositoryImpl(
    private val authApi: AuthApi
): AuthRepository {

    override suspend fun authenticate(userInput: UserInput): Token =
        authApi.authenticate(userInput = userInput).data!!


    override suspend fun refresh(refreshToken: String): Token =
        authApi.refresh(refreshToken = refreshToken).data!!

}