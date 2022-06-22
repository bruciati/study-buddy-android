package com.brc.studybuddy.data.repository.remote

import com.brc.studybuddy.domain.model.Group
import retrofit2.http.GET

interface GroupApi {

    @GET("/groups")
    suspend fun getGroups(): List<Group>

}