package com.brc.studybuddy.data.model

data class UserInput(
    val email: String,
    val authValue: String,
    val authType: AuthType
)

enum class AuthType {
    PASSWORD,
    FACEBOOK
}