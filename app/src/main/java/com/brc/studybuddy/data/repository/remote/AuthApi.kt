package com.brc.studybuddy.data.repository.remote

import com.brc.studybuddy.domain.model.Token
import com.brc.studybuddy.domain.model.UserInput
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthApi {

    @FormUrlEncoded
    @POST("/auth/refresh")
    suspend fun refresh(@Field("refreshToken") refreshToken: String): Token

    @FormUrlEncoded
    @POST("/auth/")
    suspend fun authenticate(@Field("user") userInput: UserInput): Token

}