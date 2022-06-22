package com.brc.studybuddy.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.brc.studybuddy.domain.model.AuthType
import com.brc.studybuddy.domain.model.UserInput
import com.brc.studybuddy.domain.repository.AuthRepository
import com.brc.studybuddy.presentation.util.Navigator
import com.brc.studybuddy.presentation.util.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
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

    fun performFacebookAuthentication(email: String, authValue: String) {
        viewModelScope.launch {
            val userInput = UserInput(email, authValue, AuthType.FACEBOOK)
            authRepository.authenticate(userInput)
        }.invokeOnCompletion {
            navigateToGroups()
        }
    }

    fun performNormalAuthentication(email: String, authValue: String) {
        viewModelScope.launch {
            val userInput = UserInput(email, authValue, AuthType.PASSWORD)
            authRepository.authenticate(userInput)
        }.invokeOnCompletion {
            navigateToGroups()
        }
    }

}