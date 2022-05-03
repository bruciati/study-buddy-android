package com.brc.studybuddy.domain.use_case.login

import android.util.Log
import com.brc.studybuddy.domain.model.UserInput
import com.brc.studybuddy.domain.repository.AccessTokenRepository
import com.brc.studybuddy.domain.repository.AuthRepository

class Authenticate(
    private val authRepository: AuthRepository,
    private val accessTokenRepository: AccessTokenRepository
) {

    suspend operator fun invoke(userInput: UserInput) {
        Log.i("Authenticate", "AUTH_VALUE: ${userInput.authValue}")
        val token = authRepository.authenticate(userInput)
        accessTokenRepository.save(token)
    }

}
