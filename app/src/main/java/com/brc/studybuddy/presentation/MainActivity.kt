package com.brc.studybuddy.presentation;

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.ui.graphics.Color
import com.brc.studybuddy.presentation.groups.GroupsScreen
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
