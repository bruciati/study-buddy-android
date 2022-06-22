package com.brc.studybuddy.data.repository.remote

import com.brc.studybuddy.data.repository.AccessTokenRepository
import com.brc.studybuddy.data.repository.AuthRepository
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Response
import okhttp3.Request
import okhttp3.Route

class AuthRefresher(
    private val accessTokenRepository: AccessTokenRepository,
    private val authRepository: AuthRepository
): Authenticator {

    /*
     * This function will get called only whenever the API returns a 401 (unauthorized) response code
     */
    override fun authenticate(route: Route?, response: Response): Request? {
        return runBlocking {
            accessTokenRepository.get()?.let {
                val newToken = authRepository.refresh(it.refreshToken)
                accessTokenRepository.save(newToken)
                response.request.newBuilder()
                    .header("Authorization", "Bearer ${newToken.accessToken}")
                    .build()
            }
        }
    }

}