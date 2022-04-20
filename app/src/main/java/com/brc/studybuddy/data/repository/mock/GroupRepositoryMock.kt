package com.brc.studybuddy.data.repository.mock

import android.util.Log
import com.brc.studybuddy.domain.model.Group
import com.brc.studybuddy.domain.model.User
import com.brc.studybuddy.domain.repository.GroupRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable

class GroupRepositoryMock() : GroupRepository {

    private val groupList: MutableList<Group> = mutableListOf(
        Group(0, "Super Bellissimo Gruppo", emptyList()),
        Group(1, "Gruppo dei Gigachad", emptyList()),
        Group(2, "Arrapatori di Manzi", emptyList()),
        Group(3, "Bolliti di Culi", emptyList()),
        Group(4, "Ani Anali", emptyList()),
        Group(5, "Sbiuramanzi", emptyList()),
    )

    override fun getGroups(): Observable<List<Group>> = Observable.fromArray(groupList.toList())

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