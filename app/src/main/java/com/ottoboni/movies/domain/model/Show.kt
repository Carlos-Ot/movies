package com.ottoboni.movies.domain.model

data class Show(
    val originalName: String,

    var genres: List<Genre>,

    val genreIds: List<Int>,

    val name: String,

    val popularity: Float,

    val originCountry: List<String>,

    val voteCount: Int,

    val firstAirDate: String,

    val backdropPath: String,

    val originalLanguage: String,

    val id: Int,

    val voteAverage: Float,

    val overview: String,

    val posterPath: String,

    var seasons: List<Season>
)