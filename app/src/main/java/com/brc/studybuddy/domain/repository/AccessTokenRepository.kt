package com.brc.studybuddy.domain.repository

import com.brc.studybuddy.domain.model.Token

interface AccessTokenRepository {

    suspend fun get(): Token?

    suspend fun save(token: Token)

}