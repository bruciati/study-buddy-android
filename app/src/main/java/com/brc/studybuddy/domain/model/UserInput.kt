package com.brc.studybuddy.domain.model

data class UserInput(
    val email: String,
    val authValue: String,
    val authType: AuthType
)

enum class AuthType {
    PASSWORD,
    FACEBOOK
}