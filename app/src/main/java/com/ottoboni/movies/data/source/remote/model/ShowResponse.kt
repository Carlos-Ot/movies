package com.ottoboni.movies.data.source.remote.model

import com.squareup.moshi.Json

data class ShowResponse(

    @field:Json(name = "original_name")
    val originalName: String,

    @field:Json(name = "genre_ids")
    val genreIds: List<Int>,

    @field:Json(name = "name")
    val name: String,

    @field:Json(name = "popularity")
    val popularity: Float,

    @field:Json(name = "origin_country")
    val originCountry: List<String>,

    @field:Json(name = "vote_count")
    val voteCount: Int,

    @field:Json(name = "first_air_date")
    val firstAirDate: String,

    @field:Json(name = "backdrop_path")
    val backdropPath: String,

    @field:Json(name = "original_language")
    val originalLanguage: String,

    @field:Json(name = "id")
    val id: Int,

    @field:Json(name = "vote_average")
    val voteAverage: Float,

    @field:Json(name = "overview")
    val overview: String,

    @field:Json(name = "poster_path")
    val posterPath: String,

    @field:Json(name = "genres")
    val genres: List<GenreResponse>,

    @field:Json(name = "seasons")
    val seasons: List<SeasonResponse>
)