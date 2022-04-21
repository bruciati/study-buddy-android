package com.brc.studybuddy.domain.use_case.groups

import com.brc.studybuddy.domain.model.Group
import com.brc.studybuddy.domain.repository.GroupRepository

class CreateGroup(
    private val repository: GroupRepository
) {

    suspend operator fun invoke(group: Group) {
        if(validateGroup(group)) {
            repository.createGroup(group)
        } else {
            throw Error("Inserted group is not valid")
        }
}

private fun validateGroup(group: Group): Boolean {
        /* TODO("Implement group validation") */
        return true
    }

}