package com.brc.studybuddy.data.repository.remote.endpoints

import com.brc.studybuddy.data.model.Group
import retrofit2.http.GET

interface GroupApi {

    @GET("/groups")
    suspend fun getGroups(): List<Group>

}