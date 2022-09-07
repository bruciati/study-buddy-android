package com.brc.studybuddy.presentation.login.components

import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultRegistryOwner
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Password
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.inset
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.brc.studybuddy.R
import com.brc.studybuddy.presentation.login.LoginViewModel
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import kotlinx.coroutines.flow.collect

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel()
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val errorMessageChannel = viewModel.errorMessage
    val ctx = LocalContext.current

    LaunchedEffect(Unit) {
        errorMessageChannel
            .collect {
                Toast.makeText(ctx, it, Toast.LENGTH_SHORT).show()
            }
    }

    Column(
        modifier = Modifier.padding(32.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(Modifier.height(64.dp))
        Image(
            painter = painterResource(id = R.drawable.ic_undraw_login),
            contentDescription = null,
        )
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Sign In",
                style = MaterialTheme.typography.h4
            )
            Spacer(Modifier.height(32.dp))
            IconTextField(
                email,
                "Email",
                { email = it },
                Icons.Default.Email
            )
            Spacer(Modifier.height(16.dp))
            IconTextField(
                password,
                "Password",
                { password = it },
                Icons.Default.Password
            )
            Spacer(Modifier.height(16.dp))
            Button(
                onClick = { viewModel.performNormalAuthentication(email, password) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Sign In", style = MaterialTheme.typography.button)
            }
            Spacer(Modifier.height(16.dp))
            CaptionedSeparator(text = "OR", modifier = Modifier.fillMaxWidth())
            Spacer(Modifier.height(16.dp))
            FacebookLoginButton("Login with Facebook") {
                viewModel.performFacebookAuthentication(email, it)
            }
        }
    }
}

@Composable
fun IconTextField(
    text: String,
    placeholder: String,
    onTextChange: (String) -> Unit,
    icon: ImageVector
) {
    TextField(
        modifier = Modifier
            .fillMaxWidth(),
        value = text,
        onValueChange = { onTextChange(it) },
        placeholder = {
            Text(
                modifier = Modifier
                    .alpha(ContentAlpha.medium),
                text = placeholder,
                color = Color.White
            )
        },
        textStyle = TextStyle(
            fontSize = MaterialTheme.typography.subtitle1.fontSize
        ),
        singleLine = true,
        leadingIcon = {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = Color.White
            )
        },
//        keyboardOptions = KeyboardOptions(
//            imeAction = ImeAction.Next
//        ),
//        keyboardActions = KeyboardActions(
//            onNext = {
//                onNextClicked(text)
//            }
//        ),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent,
            cursorColor = Color.White.copy(alpha = ContentAlpha.medium)
        )
    )
}

@Composable
fun CaptionedSeparator(
    text: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        val lineColor = LocalContentColor.current.copy(LocalContentAlpha.current)
        Canvas(modifier = Modifier.weight(1f)) {
            drawLine(
                start = Offset(x = 0f, y = 0f),
                end = Offset(x = size.width, y = 0f),
                color = lineColor,
                strokeWidth = 2f
            )
        }
        Text(text = text, modifier = Modifier.padding(horizontal = 12.dp))
        Canvas(modifier = Modifier.weight(1f)) {
            drawLine(
                start = Offset(x = 0f, y = 0f),
                end = Offset(x = size.width, y = 0f),
                color = lineColor,
                strokeWidth = 2f
            )
        }
    }
}

@Composable
fun FacebookLoginButton(
    text: String,
    onSuccess: (String) -> Unit
) {
    val context = LocalContext.current
    Button(
        onClick = {
            if (context is ActivityResultRegistryOwner) {
                val callbackManager = CallbackManager.Factory.create()
                val loginManager = LoginManager.getInstance()
                loginManager.registerCallback(
                    callbackManager,
                    object : FacebookCallback<LoginResult> {
                        override fun onCancel() {
                            Toast.makeText(context, "Login canceled!", Toast.LENGTH_LONG).show()
                        }

                        override fun onError(error: FacebookException) {
                            Log.e("Login", error.message ?: "Unknown error")
                            Toast.makeText(context, "Login failed with errors!", Toast.LENGTH_LONG).show()
                        }

                        override fun onSuccess(result: LoginResult) {
                            onSuccess(result.accessToken.token)
                        }
                    })
                LoginManager.getInstance().logIn(context, callbackManager, listOf("email"))
            } else {
                Toast.makeText(
                    context,
                    "This login should only happens with an AndroidX activity.",
                    Toast.LENGTH_LONG)
                    .show()
            }
        },
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(0xFF1778F2),
            contentColor = Color.White
        )
    ) {
        Text(text = text, style = MaterialTheme.typography.button)
    }
}