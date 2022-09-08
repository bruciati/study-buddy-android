package com.brc.studybuddy.presentation.registration

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.brc.studybuddy.data.model.AuthType
import com.brc.studybuddy.data.model.UserInput
import com.brc.studybuddy.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authRepository: AuthRepository
): ViewModel() {

    fun register(email: String, password: String) {
        val userInput = UserInput(email, password, AuthType.PASSWORD)
        viewModelScope.launch {
            authRepository.register(userInput)
        }
    }

}