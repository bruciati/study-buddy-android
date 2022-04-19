package com.brc.studybuddy.presentation.groups.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.brc.studybuddy.presentation.groups.GroupsViewModel

@Composable
fun GroupsScreen(
    viewModel: GroupsViewModel = hiltViewModel()
) {

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.addMockGroup()
                },
                backgroundColor = MaterialTheme.colors.primary
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Group")
            }
        }
    ) {
        Column(Modifier.padding(8.dp)) {
            Text(text = "Groups", style = MaterialTheme.typography.h5)
            Spacer(Modifier.height(16.dp))
            LazyColumn() {
                items(viewModel.state.value.groups) {
                    GroupItem(group = it)
                }
            }
        }
    }

}
