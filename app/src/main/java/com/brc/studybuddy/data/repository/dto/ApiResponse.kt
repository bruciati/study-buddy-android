package com.brc.studybuddy.data.repository.dto

data class ApiResponse<T>(
    val ok: Boolean,
    val data: T?
)
