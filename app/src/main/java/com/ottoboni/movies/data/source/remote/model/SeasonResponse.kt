package com.ottoboni.movies.data.source.remote.model

import com.squareup.moshi.Json

data class SeasonResponse(
    @field:Json(name = "air_date")
    val airDate: String,

    @field:Json(name = "episode_count")
    val episodeCount: Int,

    @field:Json(name = "id")
    val id: Int,

    @field:Json(name = "name")
    val name: String,

    @field:Json(name = "overview")
    val overview: String,

    @field:Json(name = "poster_path")
    val posterPath: String,

    @field:Json(name = "season_number")
    val seasonNumber: Int,

    @field:Json(name = "episodes")
    val episodes: List<EpisodeResponse>
)
