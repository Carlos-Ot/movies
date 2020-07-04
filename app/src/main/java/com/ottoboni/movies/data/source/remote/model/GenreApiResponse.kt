package com.ottoboni.movies.data.source.remote.model

import com.squareup.moshi.Json

data class GenreApiResponse(@Json(name = "genres") val genres: List<GenreResponse>?)
