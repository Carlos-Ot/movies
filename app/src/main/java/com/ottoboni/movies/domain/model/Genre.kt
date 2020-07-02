package com.ottoboni.movies.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import javax.inject.Inject

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