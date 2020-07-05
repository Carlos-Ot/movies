package com.ottoboni.movies.data.source.remote.model

import com.squareup.moshi.Json
import javax.inject.Inject

data class GenreResponse @Inject constructor(
    @Json(name = "id")
    val id: Int?,
    @Json(name = "name")
    val name: String?
)
