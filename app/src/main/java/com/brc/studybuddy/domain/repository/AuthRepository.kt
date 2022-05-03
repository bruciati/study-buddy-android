package com.brc.studybuddy.domain.repository

import com.brc.studybuddy.domain.model.Token
import com.brc.studybuddy.domain.model.UserInput

interface AuthRepository {

    suspend fun authenticate(userInput: UserInput): Token

}