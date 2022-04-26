package com.brc.studybuddy.data.repository.mock

import com.brc.studybuddy.domain.model.AccessToken
import com.brc.studybuddy.domain.model.UserInput
import com.brc.studybuddy.domain.repository.AuthRepository

class AuthRepositoryMock : AuthRepository {

    override suspend fun authenticate(userInput: UserInput): AccessToken {
        TODO("Not yet implemented")
    }

}