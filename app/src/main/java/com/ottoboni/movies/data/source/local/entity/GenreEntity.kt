package com.ottoboni.movies.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_genres")
data class GenreEntity(
    @PrimaryKey
    val id: Int?,
    val name: String?
)
