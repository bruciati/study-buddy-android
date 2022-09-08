package com.brc.studybuddy.presentation.login

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.brc.studybuddy.data.model.AuthType
import com.brc.studybuddy.data.model.UserInput
import com.brc.studybuddy.data.repository.AccessTokenRepository
import com.brc.studybuddy.data.repository.AuthRepository
import com.brc.studybuddy.presentation.util.Navigator
import com.brc.studybuddy.presentation.util.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import java.lang.NullPointerException
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val accessTokenRepository: AccessTokenRepository
): ViewModel() {

    // Mutable state is not visible to the outside
    private val _errorMessage: MutableSharedFlow<String?> = MutableSharedFlow()
    val errorMessage = _errorMessage.asSharedFlow()

    fun performFacebookAuthentication(email: String, token: String) =
        authenticate(UserInput(email, token, AuthType.FACEBOOK))

    fun performNormalAuthentication(email: String, password: String) =
        authenticate(UserInput(email, password, AuthType.PASSWORD))

    private fun authenticate(userInput: UserInput) =
        viewModelScope.launch {
            // we need to check errors here
            // TODO: token is taken from the repo, but what if the token is not taken (server error?)
            // find a way to handle elegantly errors.
            // You can throw the error inside the data layer (repository implementation) when you check the response body (json object)
            try {
                val token = authRepository.authenticate(userInput)
                accessTokenRepository.save(token)
            } catch (e: Exception) {
                // Emit the error message back to the view
                _errorMessage.emit(e.message)
                // Cancel the job
                this.cancel(e.message!!, e)
            }
        }.invokeOnCompletion {
            if(it == null) {
                // Iterator is null, it means that there are no Throwable(s), then we can
                // save the token and then navigate to the Groups Screen
                Navigator.navigateTo(Screen.GroupsScreen)
            } else {
                Log.e("Error", "Exception", it)
            }
        }

    fun navigateToRegister() {
        Navigator.navigateTo(Screen.RegisterScreen)
    }

}