package com.ottoboni.movies.data.source.remote.model

import com.squareup.moshi.Json

data class ApiResponse(

    @Json(name = "page")
    val page: Int?,

    @Json(name = "total_results")
    val totalResults: Int?,

    @Json(name = "total_pages")
    val totalPages: Int?,

    @Json(name = "results")
    val results: List<ShowResponse>?
)
