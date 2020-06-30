package com.ottoboni.movies.domain.model

import javax.inject.Inject

data class Genre @Inject constructor(
    val id: Int,
    val name: String
)