package com.brc.studybuddy.domain.model

data class Token(
    val accessToken: String,
    val refreshToken: String,
)