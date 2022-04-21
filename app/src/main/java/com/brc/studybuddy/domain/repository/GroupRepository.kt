package com.brc.studybuddy.domain.repository

import com.brc.studybuddy.domain.model.Group
import com.brc.studybuddy.domain.model.User

interface GroupRepository {

    suspend fun getGroups(): List<Group>

    suspend fun createGroup(group: Group)

    suspend fun addMember(groupId: Int, user: User)

}