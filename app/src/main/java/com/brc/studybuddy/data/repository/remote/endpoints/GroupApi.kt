package com.brc.studybuddy.data.repository.remote.endpoints

import com.brc.studybuddy.data.model.ApiResponse
import com.brc.studybuddy.data.model.GraphQlResponse
import com.brc.studybuddy.data.repository.remote.utils.GraphQlQuery
import retrofit2.http.Body
import retrofit2.http.POST

interface GroupApi {

    @POST("/graphql")
    suspend fun getGroups(@Body query: GraphQlQuery): ApiResponse<GraphQlResponse>

}