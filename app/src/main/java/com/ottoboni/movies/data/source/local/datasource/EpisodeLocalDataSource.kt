package com.ottoboni.movies.data.source.local.datasource

import com.ottoboni.movies.domain.model.Episode

interface EpisodeLocalDataSource {
    suspend fun getBySeason(seasonId: Int): List<Episode>?

    suspend fun save(episode: Episode)

    suspend fun delete(episode: Episode)

    suspend fun getAll(): List<Episode>?

    suspend fun getById(id: Int): Episode?
}
