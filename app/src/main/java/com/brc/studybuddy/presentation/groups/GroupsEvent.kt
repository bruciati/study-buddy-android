package com.brc.studybuddy.presentation.groups

/*
 * Describes actions that can occur on the Groups screen
 */
sealed class GroupsEvent {

    data class SearchButtonClicked(val title: String): GroupsEvent()

    data class SearchSectionChanged(val text: String): GroupsEvent()

}
