package com.brc.studybuddy.presentation;

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.brc.studybuddy.presentation.groups.components.GroupsScreen
import com.brc.studybuddy.presentation.login.components.LoginScreen
import com.brc.studybuddy.presentation.util.Navigator
import com.brc.studybuddy.presentation.util.Screen
import com.brc.studybuddy.ui.theme.StudyBuddyTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StudyBuddyTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()

                    LaunchedEffect("navigation") {
                        Navigator.sharedFlow.onEach {
                            navController.navigate(it.route)
                        }.launchIn(this)
                    }

                    NavHost(
                        navController = navController,
                        startDestination = Screen.LoginScreen.route
                    ) {
                        composable(route = Screen.LoginScreen.route) {
                            LoginScreen()
                        }
                        composable(route = Screen.GroupsScreen.route) {
                            GroupsScreen()
                        }
//                        composable(
//                            route = Screen.AddEditNoteScreen.route +
//                                    "?noteId={noteId}&noteColor={noteColor}",
//                            arguments = listOf(
//                                navArgument(
//                                    name = "noteId"
//                                ) {
//                                    type = NavType.IntType
//                                    defaultValue = -1
//                                },
//                                navArgument(
//                                    name = "noteColor"
//                                ) {
//                                    type = NavType.IntType
//                                    defaultValue = -1
//                                },
//                            )
//                        ) {
//                            val color = it.arguments?.getInt("noteColor") ?: -1
//                            AddEditNoteScreen(
//                                navController = navController,
//                                noteColor = color
//                            )
//                        }
                    }
                }
            }
        }
    }

}
