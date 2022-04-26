package com.brc.studybuddy.presentation.groups.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.brc.studybuddy.domain.model.Group
import com.brc.studybuddy.presentation.util.FetchStatus
import com.brc.studybuddy.presentation.groups.GroupsEvent
import com.brc.studybuddy.presentation.groups.GroupsViewModel

@Composable
fun GroupsScreen(
    viewModel: GroupsViewModel = hiltViewModel()
) {
    val state by viewModel.state

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
        },
        topBar = {
            SearchableAppBar(
                title = "Groups",
                isSearchInitiallyVisible = false,
                searchTextState = state.searchSectionContent,
                onTextChange = { viewModel.onEvent(GroupsEvent.SearchSectionChanged(it)) },
                onCloseClicked = { },
                onSearchClicked = { viewModel.onEvent(GroupsEvent.SearchButtonClicked(it)) },
            )
        }
    ) {
        when (val status = state.fetchGroups) {
            is FetchStatus.Success -> {
                GroupsList(
                    popularGroups = status.result,
                    groups = status.result
                )
            }
            is FetchStatus.Loading -> {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    CircularProgressIndicator()
                }
            }
            is FetchStatus.Error -> {
                // Handle error
            }
        }
    }
}

@Composable
fun GroupsList(
    popularGroups: List<Group>,
    groups: List<Group>
) {
    Column {
        LazyColumn(
            modifier = Modifier.fillMaxHeight(),
            contentPadding = PaddingValues(16.dp, 0.dp, 16.dp, 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            item {
                Text(
                    text = "Latest Groups",
                    fontSize = MaterialTheme.typography.h5.fontSize,
                    modifier = Modifier.padding(PaddingValues(vertical = 16.dp))
                )
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    items(popularGroups) {
                        LatestGroupItem(group = it)
                    }
                }
            }
            item {
                Text(
                    text = "All Groups",
                    fontSize = MaterialTheme.typography.h5.fontSize,
                    modifier = Modifier.padding(PaddingValues(vertical = 16.dp))
                )
            }
            items(groups) {
                NormalGroupItem(group = it)
            }
        }
    }
}


@Preview
@Composable
fun GroupsScreenPreview() {
    GroupsList(
        popularGroups = listOf(
            Group(0, "Super Bellissimo Gruppo", emptyList()),
            Group(1, "Gruppo dei Gigachad", emptyList()),
            Group(2, "Arrapatori di manzi", emptyList()),
        ),
        groups = listOf(
            Group(0, "Title 1", emptyList()),
            Group(1, "Title 2", emptyList()),
            Group(2, "Title 3", emptyList()),
            Group(3, "Title 4", emptyList()),
            Group(4, "Title 5", emptyList()),
            Group(5, "Title 6", emptyList()),
        ),
    )
}