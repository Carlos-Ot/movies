package com.ottoboni.movies.data.source.local.entity

import androidx.room.Embedded
import androidx.room.Relation

data class SeasonWithEpisodes(
    @Embedded
    val season: SeasonEntity,

    @Relation(
        parentColumn = "id",
        entityColumn = "season_id",
        entity = EpisodeEntity::class
    )
    val episodes: List<EpisodeEntity> = ArrayList()
)