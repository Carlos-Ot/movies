package com.ottoboni.movies.domain.repository

import com.ottoboni.movies.data.source.local.datasource.EpisodeLocalDataSource
import com.ottoboni.movies.data.source.remote.datasource.EpisodeRemoteDataSource
import com.ottoboni.movies.domain.model.Episode
import javax.inject.Inject

class EpisodeRepository @Inject constructor(
    private val localDataSource: EpisodeLocalDataSource,
    private val remoteDataSource: EpisodeRemoteDataSource
) : IEpisodeRepository {
    override suspend fun getBy(seasonId: Int) = localDataSource.getBySeason(seasonId)

    override suspend fun save(episode: Episode) = localDataSource.save(episode)

    override suspend fun delete(episode: Episode) = localDataSource.delete(episode)

    override suspend fun getAll() = localDataSource.getAll()

    override suspend fun getById(id: Int) = localDataSource.getById(id)

    // TODO: Add Local Cache for Episode
    override suspend fun getBy(showId: Int, seasonId: Int, episodeId: Int) =
        remoteDataSource.fetchEpisode(showId, seasonId, episodeId)
}