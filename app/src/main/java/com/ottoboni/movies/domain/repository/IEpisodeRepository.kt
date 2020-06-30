package com.ottoboni.movies.domain.repository

import com.ottoboni.movies.domain.model.Episode

interface IEpisodeRepository {
    suspend fun getBy(seasonId: Int): List<Episode>?

    suspend fun getBy(showId: Int, seasonId: Int, episodeId: Int): Episode?

    suspend fun save(episode: Episode)

    suspend fun delete(episode: Episode)

    suspend fun getAll(): List<Episode>?

    suspend fun getById(id: Int): Episode?
}