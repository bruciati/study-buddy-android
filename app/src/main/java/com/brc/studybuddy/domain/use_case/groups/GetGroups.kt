package com.brc.studybuddy.domain.use_case.groups

import com.brc.studybuddy.domain.model.Group
import com.brc.studybuddy.domain.repository.GroupRepository
import io.reactivex.rxjava3.core.Observable

class GetGroups(
    private val repository: GroupRepository
) {

    operator fun invoke(): Observable<List<Group>> = repository.getGroups()

}