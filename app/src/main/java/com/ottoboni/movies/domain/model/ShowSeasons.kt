package com.ottoboni.movies.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import javax.inject.Inject

@Parcelize
data class ShowSeasons @Inject constructor(
    val show: Show,
    val seasons: List<Season>
) : Parcelable