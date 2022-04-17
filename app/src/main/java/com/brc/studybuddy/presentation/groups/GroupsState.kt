package com.brc.studybuddy.presentation.groups

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.brc.studybuddy.domain.model.Group

/*
 * Holds the state of the Groups screen
 */
data class GroupsState(

    val groups: State<List<Group>> = mutableStateOf(listOf()),
    val isSearchSectionVisible: Boolean = false

)

