package com.brc.studybuddy.presentation.groups

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.brc.studybuddy.domain.model.Group
import com.brc.studybuddy.domain.use_case.groups.GroupUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class GroupsViewModel @Inject constructor(
    private val groupUseCases: GroupUseCases
): ViewModel() {

    private val _state = mutableStateOf(GroupsState())
    val state: State<GroupsState> = _state
    private var getGroupsDisposable: Disposable? = null

    init {
        getGroups()
    }

    fun onEvent(event: GroupsEvent) {
        // TODO: Implement
    }

    /* TODO: To delete, just for testing purposes */
    fun addMockGroup() {
        val milli = System.currentTimeMillis().toInt()
        groupUseCases.createGroup(
            Group(milli, milli.toString(), listOf())
        )
        .doOnComplete { getGroups() }
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe()
    }

    private fun getGroups() {
        getGroupsDisposable?.dispose()
        getGroupsDisposable = groupUseCases
            .getGroups()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                _state.value = state.value.copy(groups = it)
            }
    }
}