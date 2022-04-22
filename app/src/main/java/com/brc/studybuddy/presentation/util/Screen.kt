package com.brc.studybuddy.presentation.util


sealed class Screen(val route: String) {
    object GroupsScreen: Screen("groups_screen")
    object LoginScreen: Screen("login_screen")
}