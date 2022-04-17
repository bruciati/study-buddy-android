package com.brc.studybuddy.domain.model

data class Group(
    val id: Int,
    val title: String,
    val members: List<User>
)
