package com.brc.studybuddy.presentation.groups

import com.brc.studybuddy.domain.use_case.groups.util.GroupSearchParameter

/*
 * Describes actions that can occur on the Groups screen
 */
sealed class GroupsEvent {

    data class Search(val searchParameters: GroupSearchParameter)

    object ToggleSearchSection: GroupsEvent()

}
