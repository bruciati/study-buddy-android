package com.brc.studybuddy.data.repository.mock

import com.brc.studybuddy.data.model.Group
import com.brc.studybuddy.data.model.User
import com.brc.studybuddy.data.repository.GroupRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.coroutineContext

class GroupRepositoryMock(
    coroutineContext: CoroutineContext = Dispatchers.IO
) : GroupRepository {

    private val groupList: MutableList<Group> = mutableListOf(
        Group(0, "Super Bellissimo Gruppo", emptyList()),
        Group(1, "Gruppo dei Gigachad", emptyList()),
        Group(2, "Arrapatori di Manzi", emptyList()),
        Group(3, "Bolliti di Culi", emptyList()),
        Group(4, "Ani Anali", emptyList()),
        Group(5, "Sbiuramanzi", emptyList()),
    )

    override suspend fun getGroups(): List<Group> = withContext(coroutineContext) {
        // Simulate a Network delay
        delay(1500)
        return@withContext groupList.toList()
    }

    override suspend fun createGroup(group: Group): Unit = withContext(coroutineContext) {
        delay(200)
        groupList.add(group)
    }

    override suspend fun addMember(groupId: Int, user: User) = withContext(coroutineContext) {
        TODO("Not yet implemented")
    }


}