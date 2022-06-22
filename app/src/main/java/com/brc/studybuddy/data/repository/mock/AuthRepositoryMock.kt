package com.brc.studybuddy.data.repository.mock

import com.brc.studybuddy.domain.model.Token
import com.brc.studybuddy.domain.model.UserInput
import com.brc.studybuddy.domain.repository.AuthRepository

class AuthRepositoryMock : AuthRepository {

    override suspend fun authenticate(userInput: UserInput): Token {
        TODO("Not yet implemented")
    }

    override suspend fun refresh(refreshToken: String): Token {
        TODO("Not yet implemented")
    }

}