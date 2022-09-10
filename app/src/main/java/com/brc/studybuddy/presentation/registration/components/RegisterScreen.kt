package com.brc.studybuddy.presentation.registration.components

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Password
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.brc.studybuddy.presentation.registration.RegisterViewModel
import com.brc.studybuddy.presentation.util.components.IconTextField
import kotlinx.coroutines.flow.collect

@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel = hiltViewModel()
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val errorMessageChannel = viewModel.toastMessage
    val ctx = LocalContext.current

    LaunchedEffect(Unit) {
        errorMessageChannel
            .collect {
                Toast.makeText(ctx, it, Toast.LENGTH_SHORT).show()
            }
    }

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(32.dp, 64.dp, 32.dp, 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        Text(
            text = "Sign Up",
            fontSize = MaterialTheme.typography.h3.fontSize
        )
        Spacer(modifier=Modifier.height(64.dp))
        IconTextField(
            email,
            "Email",
            { email = it },
            Icons.Default.Email
        )

        IconTextField(
            password,
            "Password",
            { password = it },
            Icons.Default.Password
        )

        Button(
            onClick = { viewModel.register(email, password) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Register", style = MaterialTheme.typography.button)
        }
    }
}

@Composable
fun Prototype() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(16.dp, 64.dp, 16.dp, 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        Text(
            text = "Sign Up",
            fontSize = MaterialTheme.typography.h3.fontSize
        )
        Spacer(modifier=Modifier.height(64.dp))
        IconTextField(
            email,
            "Email",
            { email = it },
            Icons.Default.Email
        )

        IconTextField(
            password,
            "Password",
            { email = it },
            Icons.Default.Password
        )

        Button(
            onClick = { /* TODO: Launch ViewModel's registration */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Register", style = MaterialTheme.typography.button)
        }
    }
}



@Preview
@Composable
fun PrototypePreview() {
    Prototype()
}