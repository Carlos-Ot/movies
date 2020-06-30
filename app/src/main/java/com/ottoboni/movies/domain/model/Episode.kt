package com.ottoboni.movies.domain.model

import javax.inject.Inject

data class Episode @Inject constructor(
    val episodeNumber: Int,

    val airDate: String,

    val name: String,

    val overview: String,

    val id: Int,

    val stillPath: String,

    val voteAverage: Float,

    var seasonId: Int
)