package com.ottoboni.movies.data.source.remote.model

import javax.inject.Inject

data class GenreResponse @Inject constructor(
    val id: Int?,
    val name: String?
)
