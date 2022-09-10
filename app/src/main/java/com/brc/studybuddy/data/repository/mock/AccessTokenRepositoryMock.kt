package com.brc.studybuddy.data.repository.mock

import com.brc.studybuddy.data.model.Token
import com.brc.studybuddy.data.repository.AccessTokenRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import java.util.logging.Logger
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.coroutineContext

class AccessTokenRepositoryMock(
    coroutineContext: CoroutineContext = Dispatchers.IO
): AccessTokenRepository {

    private var fakeAccessToken: String = "eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiIxIiwiaWF0IjoxNjYyODI1NjgwLCJpc1JlZnJlc2hUb2tlbiI6ZmFsc2UsImV4cCI6MTY2MzEyNTY4MH0.YcWWGK3nScrAn6T5bxl0iFbC6bbJaoyvCQRrCNG_j4RpWdgKhCw0xzZNxpvwuHfr"
    private var fakeRefreshToken: String = "eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiIxIiwiaWF0IjoxNjYyNzM2MDA4LCJpc1JlZnJlc2hUb2tlbiI6dHJ1ZSwiZXhwIjoxNjYzMDM2MTI4fQ.sPKPysML9sc1xyHt8qxSbaF7grhek8AhTSBpf5sS7ouNOxG056ciY0jEMu_B-8H8"
    private val LOGGER = Logger.getLogger(AccessTokenRepositoryMock::class.java.name)

    override suspend fun get(): Token = withContext(coroutineContext) {
        delay(50)
        LOGGER.info("Called get: $fakeAccessToken")
        return@withContext Token(fakeAccessToken, fakeRefreshToken)
    }

    override suspend fun save(token: Token) = withContext(coroutineContext) {
        delay(50)
//        fakeAccessToken = token.accessToken
//        fakeRefreshToken = token.refreshToken
        LOGGER.info("Called save: $fakeAccessToken")
    }

}