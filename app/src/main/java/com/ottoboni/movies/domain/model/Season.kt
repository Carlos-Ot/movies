package com.ottoboni.movies.domain.model

import android.os.Parcelable
import javax.inject.Inject
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Season @Inject constructor(
    val airDate: String,

    val episodeCount: Int,

    val id: Int,

    val name: String,

    val overview: String,

    val posterPath: String,

    val seasonNumber: Int,

    var showId: Int,

    val episodes: List<Episode>
) : Parcelable
