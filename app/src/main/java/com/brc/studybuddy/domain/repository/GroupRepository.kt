package com.brc.studybuddy.domain.repository

import com.brc.studybuddy.domain.model.Group
import com.brc.studybuddy.domain.model.User
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable

interface GroupRepository {

    fun getGroups(): Observable<List<Group>>

    fun createGroup(group: Group): Completable

    fun addMember(groupId: Int, user: User): Completable

}