package com.brc.studybuddy.domain.use_case.groups

import com.brc.studybuddy.domain.model.Group
import com.brc.studybuddy.domain.repository.GroupRepository

class GetGroups(
    private val repository: GroupRepository
) {

    suspend operator fun invoke(): List<Group> = repository.getGroups()

}