package com.brc.studybuddy.domain.use_case.login

import com.brc.studybuddy.domain.repository.AccessTokenRepository
import com.brc.studybuddy.domain.repository.AuthRepository
import com.brc.studybuddy.domain.model.UserInput
import android.util.Log

class Authenticate(
    private val authRepository: AuthRepository,
    private val accessTokenRepository: AccessTokenRepository
) {

    suspend operator fun invoke(userInput: UserInput) {
        val token = authRepository.authenticate(userInput)
        Log.i("Authenticate", "RESPONSE: $token")
        accessTokenRepository.save(token)
    }

}
