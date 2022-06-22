package com.brc.studybuddy.domain.repository.remote.endpoints

import com.brc.studybuddy.domain.model.ApiResponse
import com.brc.studybuddy.domain.model.Token
import com.brc.studybuddy.domain.model.UserInput
import retrofit2.http.*

interface AuthApi {

    @Headers("Content-Type: application/json")
    @POST("/auth/refresh")
    suspend fun refresh(@Body refreshToken: String): ApiResponse<Token>

    @Headers("Content-Type: application/json")
    @POST("/auth/")
    suspend fun authenticate(@Body userInput: UserInput): ApiResponse<Token>

}
