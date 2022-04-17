package com.brc.studybuddy.domain.use_case.groups.util

sealed class GroupSearchParameter {

    data class Title(val title: String) : GroupSearchParameter()

}
