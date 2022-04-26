package com.brc.studybuddy.presentation.login

import androidx.lifecycle.ViewModel
import com.brc.studybuddy.domain.model.AuthType
import com.brc.studybuddy.domain.model.UserInput
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

    fun performFacebookAuthentication(email: String, authValue: String) {
        loginUseCases.facebookLogin(
            UserInput(email = email, authType = AuthType.FACEBOOK, authValue = authValue)
        )
    }

    fun performNormalAuthentication(email: String, authValue: String) {
        loginUseCases.normalLogin(
            UserInput(email = email, authType = AuthType.PASSWORD, authValue = authValue)
        )
    }

}