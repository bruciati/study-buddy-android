package com.brc.studybuddy.presentation.login

import androidx.lifecycle.ViewModel
import com.brc.studybuddy.domain.use_case.login.LoginUseCases
import com.brc.studybuddy.presentation.util.Navigator
import com.brc.studybuddy.presentation.util.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCases: LoginUseCases
): ViewModel() {

    // TODO: Refactor
    fun navigateToGroups() {
        Navigator.navigateTo(Screen.GroupsScreen)
    }

}