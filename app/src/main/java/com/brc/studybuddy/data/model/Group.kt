package com.brc.studybuddy.data.model

data class Group(
    val id: Int,
    val title: String,
    val members: List<User>? = null
)
