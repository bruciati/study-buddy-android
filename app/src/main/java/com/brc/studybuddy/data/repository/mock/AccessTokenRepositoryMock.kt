package com.brc.studybuddy.data.repository.mock

import com.brc.studybuddy.domain.model.AccessToken
import com.brc.studybuddy.domain.repository.AccessTokenRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.coroutineContext

class AccessTokenRepositoryMock(
    coroutineContext: CoroutineContext = Dispatchers.IO
): AccessTokenRepository {

    private var fakeToken: String = "FAKE_TOKEN"

    override suspend fun get(): AccessToken = withContext(coroutineContext) {
        delay(50)
        return@withContext AccessToken(fakeToken)
    }

    override suspend fun save(token: AccessToken) = withContext(coroutineContext) {
        delay(50)
        fakeToken = token.token
    }

}