package com.brc.studybuddy.domain.model

data class ApiResponse<T>(
    val ok: Boolean,
    val data: T?
)
