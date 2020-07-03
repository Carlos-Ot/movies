package com.ottoboni.movies.data.source.local.datasource

import com.ottoboni.movies.domain.model.Show

interface ShowLocalDataSource {
    suspend fun getShowsWithSeasons(): List<Show>?

    suspend fun getShowWithSeasonById(showId: Int): Show?

    suspend fun save(show: Show)

    suspend fun delete(show: Show)

    suspend fun getAll(): List<Show>?

    suspend fun getById(id: Int): Show?
}
