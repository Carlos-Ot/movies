package com.ottoboni.movies.data.source.remote.model

import com.squareup.moshi.Json

data class EpisodeResponse(

    @field:Json(name = "episode_number")
    val episodeNumber: Int?,

    @field:Json(name = "air_date")
    val airDate: String?,

    @field:Json(name = "name")
    val name: String?,

    @field:Json(name = "overview")
    val overview: String?,

    @field:Json(name = "id")
    val id: Int?,

    @field:Json(name = "still_path")
    val stillPath: String?,

    @field:Json(name = "vote_average")
    val voteAverage: Float?

)
