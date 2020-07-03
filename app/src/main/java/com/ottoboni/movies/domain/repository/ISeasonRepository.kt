package com.ottoboni.movies.domain.repository

import com.ottoboni.movies.domain.model.Season

interface ISeasonRepository {
    suspend fun getBySeasonNumber(seasonNumber: Int): Season?

    suspend fun getSeasonsWithEpisodes(showId: Int): List<Season>?

    suspend fun getSeasonWithEpidosesById(seasonId: Int): Season?

    suspend fun fetchSeasonByNumber(showId: Int, seasonNumber: Int): Season?

    suspend fun save(season: Season)

    suspend fun delete(season: Season)

    suspend fun getAll(): List<Season>?

    suspend fun getById(id: Int): Season?
}
