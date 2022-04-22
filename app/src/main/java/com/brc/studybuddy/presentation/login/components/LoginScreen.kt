package com.brc.studybuddy.presentation.login.components

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.brc.studybuddy.R
import com.brc.studybuddy.presentation.login.LoginViewModel

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel()
) {
    val email by remember { mutableStateOf("") }
    val password by remember { mutableStateOf("") }

    Column(Modifier.padding(32.dp)) {
        Spacer(Modifier.height(64.dp))
        Image(
            painter = painterResource(id = R.drawable.ic_undraw_login),
            contentDescription = null,
        )
        Column(
            modifier = Modifier.fillMaxHeight(),
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
                {},
                Icons.Default.Email
            )
            Spacer(Modifier.height(16.dp))
            IconTextField(
                password,
                "Password",
                {},
                Icons.Default.Password
            )
            Spacer(Modifier.height(16.dp))
            Button(
                onClick = { viewModel.navigateToGroups() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Sign In", style = MaterialTheme.typography.button)
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

@Preview
@Composable
fun LoginScreenPreview() = LoginScreen()
