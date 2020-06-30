package com.ottoboni.movies.data.source.local.datasource

import com.ottoboni.movies.domain.model.Season

interface SeasonLocalDataSource {
    suspend fun getBySeasonNumber(seasonNumber: Int): Season?

    suspend fun getSeasonsWithEpisodesByShow(showId: Int): List<Season>?

    suspend fun getSeasonWithEpisodesById(seasonId: Int): Season?

    suspend fun save(season: Season)

    suspend fun delete(season: Season)

    suspend fun getAll(): List<Season>?

    suspend fun getById(id: Int): Season?
}