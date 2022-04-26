package com.brc.studybuddy.presentation.groups

import com.brc.studybuddy.domain.model.Group
import com.brc.studybuddy.presentation.util.FetchStatus

/*
 * Holds the state of the Groups screen
 */
data class GroupsState(

    val searchSectionContent: String = "",

    val fetchGroups: FetchStatus<List<Group>> = FetchStatus.fromValue(emptyList())

)
