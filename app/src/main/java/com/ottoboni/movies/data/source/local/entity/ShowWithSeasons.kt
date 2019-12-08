package com.ottoboni.movies.data.source.local.entity

import androidx.room.Embedded
import androidx.room.Relation

data class ShowWithSeasons(
    @Embedded
    val show: ShowEntity,

    @Relation(
        parentColumn = "id",
        entityColumn = "show_id"
    )
    val seasons: List<SeasonEntity> = ArrayList()
)