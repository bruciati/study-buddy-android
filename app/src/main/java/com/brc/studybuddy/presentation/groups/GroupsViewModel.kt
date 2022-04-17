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

    val groups: MutableLiveData<List<Group>> = MutableLiveData()
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
            Group(
                milli,
                milli.toString(),
                listOf()
            )
        )
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe()

        getGroups()
    }

    fun getGroupsObservable(): Observable<List<Group>> = groupUseCases
        .getGroups()
        .delay(2, TimeUnit.SECONDS)
        .subscribeOn(Schedulers.computation())
        .observeOn(AndroidSchedulers.mainThread())

    private fun getGroups() {
        getGroupsDisposable?.dispose()
        getGroupsDisposable = groupUseCases
            .getGroups()
            .delay(2, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                groups.value = it
            }
    }
}