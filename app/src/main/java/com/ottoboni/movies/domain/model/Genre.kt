package com.ottoboni.movies.domain.model

import android.os.Parcelable
import javax.inject.Inject
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Genre @Inject constructor(
    val id: Int,
    val name: String
) : Parcelable

fun List<Genre>.toPlainText() = StringBuilder().apply {
    forEachIndexed { index: Int, genre: Genre ->
        append(genre.name)
        if (index < size - 1) append(", ")
        else append('.')
    }
}
