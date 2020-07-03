package com.ottoboni.movies.domain.model

import android.os.Parcelable
import javax.inject.Inject
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ShowSeasons @Inject constructor(
    val show: Show,
    val seasons: List<Season>
) : Parcelable
