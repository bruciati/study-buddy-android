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

    private var fakeAccessToken: String = "FAKE_ACCESS_TOKEN"
    private var fakeRefreshToken: String = "FAKE_REFRESH_TOKEN"
    private val LOGGER = Logger.getLogger(AccessTokenRepositoryMock::class.java.name)

    override suspend fun get(): Token = withContext(coroutineContext) {
        delay(50)
        LOGGER.info("Called get: $fakeAccessToken")
        return@withContext Token(fakeAccessToken, fakeRefreshToken)
    }

    override suspend fun save(token: Token) = withContext(coroutineContext) {
        delay(50)
        fakeAccessToken = token.accessToken
        fakeRefreshToken = token.refreshToken
        LOGGER.info("Called save: $fakeAccessToken")
    }

}