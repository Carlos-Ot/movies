package com.ottoboni.movies.data.source.remote.model.enums

import com.squareup.moshi.Json

enum class MediaType {
    @Json(name = "all")
    ALL,

    @Json(name = "movie")
    MOVIE,

    @Json(name = "tv")
    TV,

    @Json(name = "person")
    PERSON
}