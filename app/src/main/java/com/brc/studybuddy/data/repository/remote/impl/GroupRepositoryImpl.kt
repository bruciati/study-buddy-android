package com.brc.studybuddy.data.repository.remote.impl

import com.brc.studybuddy.data.model.Group
import com.brc.studybuddy.data.model.User
import com.brc.studybuddy.data.repository.GroupRepository
import com.brc.studybuddy.data.repository.remote.utils.Queries
import com.brc.studybuddy.data.repository.remote.endpoints.GroupApi

class GroupRepositoryImpl(private val groupApi: GroupApi): GroupRepository {
    override suspend fun getGroups(): List<Group> = groupApi.getGroups(Queries.GROUP_LIST_QUERY).data?.groups!!

    override suspend fun createGroup(group: Group) {
        TODO("Not yet implemented")
    }

    override suspend fun addMember(groupId: Int, user: User) {
        TODO("Not yet implemented")
    }
}