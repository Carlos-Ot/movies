package com.ottoboni.movies.domain.model

data class Episode(
    val episodeNumber: Int,

    val airDate: String,

    val name: String,

    val overview: String,

    val id: Int,

    val stillPath: String,

    val voteAverage: Float,

    var seasonId: Int
)