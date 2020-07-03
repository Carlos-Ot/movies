package com.ottoboni.movies.domain.model

import android.os.Parcelable
import javax.inject.Inject
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Show @Inject constructor(
    val originalName: String?,

    var genres: List<Genre>?,

    val genreIds: List<Int>?,

    val name: String?,

    val popularity: Float?,

    val originCountry: List<String>?,

    val voteCount: Int?,

    val firstAirDate: String?,

    val backdropUrl: String?,

    val originalLanguage: String?,

    val id: Int?,

    val voteAverage: Float?,

    val overview: String?,

    val posterUrl: String?,

    var seasons: List<Season>?
) : Parcelable

fun List<Show>.setGenres(genres: List<Genre>?) =
    forEach { show ->
        show.apply {
            this.genres = genres?.filter { genre -> genreIds?.contains(genre.id) ?: false }
        }
    }
