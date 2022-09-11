package com.brc.studybuddy.presentation.addgroup.components

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Title
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.brc.studybuddy.presentation.addgroup.AddGroupViewModel
import com.brc.studybuddy.presentation.util.components.IconTextField

@Composable
fun AddGroupScreen(
    viewModel: AddGroupViewModel = hiltViewModel()
) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    val errorMessageChannel = viewModel.toastMessage
    val ctx = LocalContext.current

    LaunchedEffect(Unit) {
        errorMessageChannel
            .collect {
                Toast.makeText(ctx, it, Toast.LENGTH_SHORT).show()
            }
    }

    Column {
        TopAppBar(
            title = { Text(text = "Create a new Group") }
        )
        Column(
            modifier = Modifier.padding(32.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            IconTextField(
                text = title,
                placeholder = "Title",
                onTextChange = { title = it },
                icon = Icons.Default.Title
            )

            IconTextField(
                text = description,
                placeholder = "Description",
                onTextChange = { description = it },
                icon = Icons.Default.Description,
                maxLines = 5,
            )

            Button(
                onClick = { viewModel.addGroup(title, description) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Add", style = MaterialTheme.typography.button)
            }

        }

    }
}

@Composable
@Preview
fun AddGroupScreenPreview() {
    AddGroupScreen()
}
