package com.brc.studybuddy.domain.repository

import com.brc.studybuddy.domain.model.AccessToken

interface AccessTokenRepository {

    suspend fun get(): AccessToken
    suspend fun save(token: AccessToken)

}