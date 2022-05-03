package com.brc.studybuddy.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.brc.studybuddy.domain.model.AuthType
import com.brc.studybuddy.domain.model.UserInput
import com.brc.studybuddy.domain.use_case.login.Authenticate
import com.brc.studybuddy.presentation.util.Navigator
import com.brc.studybuddy.presentation.util.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authenticate: Authenticate
) : ViewModel() {

    // TODO: Refactor
    fun navigateToGroups() {
        Navigator.navigateTo(Screen.GroupsScreen)
    }

    fun performFacebookAuthentication(email: String, authValue: String) {
        viewModelScope.launch {
            authenticate(
                UserInput(email = email, authType = AuthType.FACEBOOK, authValue = authValue)
            )
        }
    }

    fun performNormalAuthentication(email: String, authValue: String) {
        viewModelScope.launch {
            authenticate(
                UserInput(email = email, authType = AuthType.PASSWORD, authValue = authValue)
            )
        }
    }

}