package com.ottoboni.movies.data.source.remote.model

import com.squareup.moshi.Json

data class ApiResponse(

    @field:Json(name = "page")
    val page: Int?,

    @field:Json(name = "total_results")
    val totalResults: Int?,

    @field:Json(name = "total_pages")
    val totalPages: Int?,

    @field:Json(name = "results")
    val results: List<ShowResponse>?
)
