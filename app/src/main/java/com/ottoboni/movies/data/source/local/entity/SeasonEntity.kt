package com.ottoboni.movies.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_season")
data class SeasonEntity(
    @ColumnInfo(name = "air_date")
    val airDate: String,

    @ColumnInfo(name = "episode_count")
    val episodeCount: Int,

    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "overview")
    val overview: String,

    @ColumnInfo(name = "poster_path")
    val posterPath: String,

    @ColumnInfo(name = "season_number")
    val seasonNumber: Int,

    @ColumnInfo(name = "show_id")
    val showId: Int
)
