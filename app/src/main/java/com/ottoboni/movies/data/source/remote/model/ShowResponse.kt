package com.ottoboni.movies.data.source.remote.model

import com.squareup.moshi.Json

data class ShowResponse(

    @Json(name = "original_name")
    val originalName: String?,

    @Json(name = "genre_ids")
    val genreIds: List<Int>?,

    @Json(name = "name")
    val name: String?,

    @Json(name = "popularity")
    val popularity: Float?,

    @Json(name = "origin_country")
    val originCountry: List<String>?,

    @Json(name = "vote_count")
    val voteCount: Int?,

    @Json(name = "first_air_date")
    val firstAirDate: String?,

    @Json(name = "backdrop_path")
    val backdropPath: String?,

    @Json(name = "original_language")
    val originalLanguage: String?,

    @Json(name = "id")
    val id: Int?,

    @Json(name = "vote_average")
    val voteAverage: Float?,

    @Json(name = "overview")
    val overview: String?,

    @Json(name = "poster_path")
    val posterPath: String?,

    @Json(name = "genres")
    val genres: List<GenreResponse>?,

    @Json(name = "seasons")
    val seasons: List<SeasonResponse>?
)
