package com.ottoboni.movies.data.repository

import com.ottoboni.movies.domain.datasource.episode.EpisodeDataSource
import com.ottoboni.movies.domain.datasource.episode.IEpisodeLocalDataSource
import com.ottoboni.movies.domain.datasource.episode.IEpisodeRemoteDataSource
import com.ottoboni.movies.domain.model.Episode
import com.ottoboni.movies.domain.model.parser.EpisodeParser

class EpisodeRepository(
    private val localDataSource: IEpisodeLocalDataSource,
    private val remoteDataSource: IEpisodeRemoteDataSource
) : EpisodeDataSource {
    override suspend fun getBySeason(seasonId: Int): List<Episode> {
        return localDataSource.getBySeason(seasonId)
    }

    override suspend fun save(domain: Episode) {
        localDataSource.save(domain)
    }

    override suspend fun update(domain: Episode) {
        localDataSource.update(domain)
    }

    override suspend fun delete(domain: Episode) {
        localDataSource.delete(domain)
    }

    override suspend fun getAll(): List<Episode> {
        return localDataSource.getAll()
    }

    override suspend fun getById(id: Int): Episode {
        return localDataSource.getById(id)
    }

    override suspend fun loadEpisode(showId: Int, seasonId: Int, episodeId: Int): Episode {
        val episodeResponse = remoteDataSource.fetchEpisodeAsync(showId, seasonId, episodeId).await()

        val episode = EpisodeParser.parse(episodeResponse).apply { this.seasonId = seasonId }

        localDataSource.save(episode)

        return episode
    }
}