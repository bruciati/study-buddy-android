package com.brc.studybuddy.data.repository.remote.impl

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.Optional
import com.brc.studybuddy.GroupsListQuery
import com.brc.studybuddy.InsertGroupMutation
import com.brc.studybuddy.data.model.Group
import com.brc.studybuddy.data.model.User
import com.brc.studybuddy.data.repository.GroupRepository
import com.brc.studybuddy.type.GroupInput

class GroupRepositoryImpl(private val apolloClient: ApolloClient): GroupRepository {
    override suspend fun getGroups(): List<Group> {
        val response = try { apolloClient.query(GroupsListQuery()).execute() } catch(e: Exception) { throw e }
        return response.data
            ?.groups
            ?.map { g -> Group(
                g.id.toInt(),
                g.title,
                g.description
            )}!!
    }

    override suspend fun createGroup(group: Group) {
        val input = GroupInput(
            title = Optional.presentIfNotNull(group.title),
            description = Optional.presentIfNotNull(group.description)
        )
        val groupMutation = InsertGroupMutation(input)
        apolloClient.mutation(groupMutation).execute()
    }

    override suspend fun addMember(groupId: Int, user: User) {
        TODO("Not yet implemented")
    }

}