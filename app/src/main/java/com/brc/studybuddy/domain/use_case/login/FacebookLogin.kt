package com.brc.studybuddy.domain.use_case.login

import android.util.Log
import com.brc.studybuddy.domain.model.UserInput
import com.brc.studybuddy.domain.repository.AccessTokenRepository
import com.brc.studybuddy.domain.repository.AuthRepository

class FacebookLogin(
    val authRepository: AuthRepository,
    val accessTokenRepository: AccessTokenRepository
) {

    operator fun invoke(userInput: UserInput) {
        Log.i("FacebookLogin Use Case", "FB_ACCESS_TOKEN: ${userInput.authValue}")
        // Invoke FACEBOOK API, then push the token
    }

}