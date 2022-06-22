package com.brc.studybuddy.data.repository

import com.brc.studybuddy.data.model.Token
import com.brc.studybuddy.data.model.UserInput

interface AuthRepository {

    suspend fun authenticate(userInput: UserInput): Token

    suspend fun refresh(refreshToken: String): Token

}