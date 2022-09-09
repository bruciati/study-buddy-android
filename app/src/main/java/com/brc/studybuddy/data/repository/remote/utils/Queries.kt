package com.brc.studybuddy.data.repository.remote.utils

data class GraphQlQuery(val query: String)

object Queries {
    val GROUP_LIST_QUERY = GraphQlQuery("{ groups { id, title } }")

}