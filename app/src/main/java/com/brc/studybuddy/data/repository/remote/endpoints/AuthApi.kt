package com.brc.studybuddy.data.repository.remote.endpoints

import com.brc.studybuddy.data.model.ApiResponse
import com.brc.studybuddy.data.model.Token
import com.brc.studybuddy.data.model.UserInput
import retrofit2.http.*

interface AuthApi {

    @Headers("Content-Type: application/json")
    @POST("/auth/refresh")
    suspend fun refresh(@Body refreshToken: String): ApiResponse<Token>

    @Headers("Content-Type: application/json")
    @POST("/auth/")
    suspend fun authenticate(@Body userInput: UserInput): ApiResponse<Token>

}
