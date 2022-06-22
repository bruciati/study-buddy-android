package com.brc.studybuddy.data.repository

import com.brc.studybuddy.data.model.Token

interface AccessTokenRepository {

    suspend fun get(): Token?

    suspend fun save(token: Token)

}