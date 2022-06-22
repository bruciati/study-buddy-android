package com.brc.studybuddy.data.model

data class ApiResponse<T>(
    val ok: Boolean,
    val data: T?
)
