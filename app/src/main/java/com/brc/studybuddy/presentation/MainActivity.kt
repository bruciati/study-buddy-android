package com.brc.studybuddy.presentation;

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import com.brc.studybuddy.presentation.groups.components.GroupsScreen
import com.brc.studybuddy.ui.theme.StudyBuddyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StudyBuddyTheme {
                Surface(color = MaterialTheme.colors.background) {
                    GroupsScreen()
                }
            }
        }
    }

}
