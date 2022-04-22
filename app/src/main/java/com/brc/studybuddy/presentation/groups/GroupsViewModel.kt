package com.brc.studybuddy.presentation.groups

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.brc.studybuddy.domain.model.Group
import com.brc.studybuddy.domain.repository.FetchStatus
import com.brc.studybuddy.domain.use_case.groups.GroupUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GroupsViewModel @Inject constructor(
    private val groupUseCases: GroupUseCases
): ViewModel() {

    private val _state = mutableStateOf(GroupsState())
    val state: State<GroupsState> = _state
    private var getGroupsJob: Job? = null

    init {
        getGroups()
    }

    fun onEvent(event: GroupsEvent) {
        when(event) {
            is GroupsEvent.SearchButtonClicked -> {
                Log.i("SEARCH", "Search button clicked")
            }
            is GroupsEvent.SearchSectionChanged -> {

            }
        }
    }

    /* TODO: To delete, just for testing purposes */
    fun addMockGroup() {
        viewModelScope.launch {
            // Set loading UI state
            val milli = System.currentTimeMillis().toInt()
            groupUseCases.createGroup(Group(milli, milli.toString(), listOf()))
        }.invokeOnCompletion {
            // Set error-success UI state
            getGroups()
        }
    }

    private fun getGroups() {
        getGroupsJob?.cancel()
        getGroupsJob = viewModelScope.launch {
            // Set loading UI state
            _state.value = state.value.copy(
                fetchGroups = FetchStatus.Loading
            )
            _state.value = state.value.copy(
                fetchGroups = FetchStatus.fromValue(groupUseCases.getGroups())
            )
        }
    }

}