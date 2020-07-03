package com.ottoboni.movies.data.source.remote.model

import com.squareup.moshi.Json

data class ErrorResponse(
    @Json(name = "status_message")
    val statusMessage: String?,

    @Json(name = "status_code")
    val statusCode: Int?
)
