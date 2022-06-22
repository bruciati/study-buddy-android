package com.brc.studybuddy.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.brc.studybuddy.data.model.AuthType
import com.brc.studybuddy.data.model.UserInput
import com.brc.studybuddy.data.repository.AuthRepository
import com.brc.studybuddy.presentation.util.Navigator
import com.brc.studybuddy.presentation.util.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository
): ViewModel() {

    // TODO: Refactor
    private fun navigateToGroups() {
        Navigator.navigateTo(Screen.GroupsScreen)
    }

    fun performFacebookAuthentication(email: String, token: String) =
        authenticate(UserInput(email, token, AuthType.PASSWORD))

    fun performNormalAuthentication(email: String, password: String) =
        authenticate(UserInput(email, password, AuthType.PASSWORD))

    private fun authenticate(userInput: UserInput) =
        viewModelScope.launch {
            // we need to check errors here
            // TODO: token is taken from the repo, but what if the token is not taken (server error?)
            // find a way to handle elegantly errors.
            // You can throw the error inside the data layer (repository implementation) when you check the response body (json object)
            val token = authRepository.authenticate(userInput)
        }.invokeOnCompletion {
            navigateToGroups()
        }

}