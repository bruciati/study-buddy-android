package com.brc.studybuddy.presentation.addgroup.components

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Title
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
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

            CustomDropDownMenu()

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

@Composable
fun CustomDropDownMenu() {
    var expanded by remember { mutableStateOf(false) }
    val list = listOf("Kotlin", "Ano")
    var selectedItem by remember { mutableStateOf("") }
    var textFieldSize by remember { mutableStateOf(Size.Zero) }

    val icon = if(expanded) {
       Icons.Filled.KeyboardArrowUp
    } else {
       Icons.Filled.KeyboardArrowDown
    }

    Column(modifier = Modifier.padding(20.dp)) {
        OutlinedTextField(
            value = selectedItem,
            onValueChange = { selectedItem = it },
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    textFieldSize = coordinates.size.toSize()
                },
            label = { Text(text="Selected Item") },
            trailingIcon = {
                Icon(icon, "", Modifier.clickable { expanded = !expanded })
            }
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.width(with(LocalDensity.current){ textFieldSize.width.toDp() })
        ) {
            list.forEach { label ->
                DropdownMenuItem(onClick = {
                    selectedItem = label
                    expanded = false
                }) {
                    Text(text = label)
                }
            }
        }
    }
}