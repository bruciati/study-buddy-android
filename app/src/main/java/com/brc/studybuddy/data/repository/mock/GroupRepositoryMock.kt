package com.brc.studybuddy.data.repository.mock

import com.brc.studybuddy.domain.model.Group
import com.brc.studybuddy.domain.model.User
import com.brc.studybuddy.domain.repository.GroupRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable

class GroupRepositoryMock() : GroupRepository {

    private val groupList: MutableList<Group> = mutableListOf()

    override fun getGroups(): Observable<List<Group>> = Observable.fromArray(groupList)

    override fun createGroup(group: Group): Completable = Completable.create {
        groupList.add(group)
        it.onComplete()
    }

    override fun addMember(groupId: Int, user: User): Completable = Completable.create { emitter ->
        val group = groupList.firstOrNull { e -> e.id == groupId }
        if (group != null) {
            emitter.onComplete()
        } else {
            emitter.onError(Error("Given Group id is not valid"))
        }
    }

}