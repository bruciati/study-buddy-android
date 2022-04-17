package com.brc.studybuddy.domain.use_case.groups

import com.brc.studybuddy.domain.model.Group
import com.brc.studybuddy.domain.repository.GroupRepository
import io.reactivex.rxjava3.core.Completable

class CreateGroup(
    private val repository: GroupRepository
) {

    operator fun invoke(group: Group): Completable {
        return Completable.create {
            if(validateGroup(group)) it.onComplete()
            else it.onError(Error("Inserted group is not valid"))
        }.andThen(repository.createGroup(group))
    }

    private fun validateGroup(group: Group): Boolean {
        /* TODO("Implement group validation") */
        return true
    }

}