package com.ottoboni.movies.data.source.remote.model

import com.squareup.moshi.Json

data class EpisodeResponse(

    @Json(name = "episode_number")
    val episodeNumber: Int?,

    @Json(name = "air_date")
    val airDate: String?,

    @Json(name = "name")
    val name: String?,

    @Json(name = "overview")
    val overview: String?,

    @Json(name = "id")
    val id: Int?,

    @Json(name = "still_path")
    val stillPath: String?,

    @Json(name = "vote_average")
    val voteAverage: Float?

)
