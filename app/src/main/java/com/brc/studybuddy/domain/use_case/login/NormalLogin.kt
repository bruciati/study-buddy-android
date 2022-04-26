package com.brc.studybuddy.domain.use_case.login

import com.brc.studybuddy.domain.model.UserInput
import com.brc.studybuddy.domain.repository.AccessTokenRepository
import com.brc.studybuddy.domain.repository.AuthRepository

class NormalLogin(
    val authRepository: AuthRepository,
    val accessTokenRepository: AccessTokenRepository
) {

    operator fun invoke(userInput: UserInput) {

    }

}