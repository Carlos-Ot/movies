package com.ottoboni.movies.domain.repository

import com.ottoboni.movies.data.source.local.datasource.SeasonLocalDataSource
import com.ottoboni.movies.data.source.remote.datasource.SeasonRemoteDataSource
import com.ottoboni.movies.domain.model.Season
import javax.inject.Inject

class SeasonRepository @Inject constructor(
    private val localDataSource: SeasonLocalDataSource,
    private val remoteDataSource: SeasonRemoteDataSource
) : ISeasonRepository {
    override suspend fun getBySeasonNumber(seasonNumber: Int) =
        localDataSource.getBySeasonNumber(seasonNumber)

    override suspend fun getSeasonsWithEpisodes(showId: Int) =
        localDataSource.getSeasonsWithEpisodesByShow(showId)

    override suspend fun getSeasonWithEpidosesById(seasonId: Int) =
        localDataSource.getSeasonWithEpisodesById(seasonId)

    override suspend fun save(season: Season) = localDataSource.save(season)

    override suspend fun delete(season: Season) = localDataSource.delete(season)

    override suspend fun getAll() = localDataSource.getAll()

    override suspend fun getById(id: Int) = localDataSource.getById(id)

    // TODO: Save Season on Local Cache
    override suspend fun fetchSeasonByNumber(showId: Int, seasonNumber: Int) =
        remoteDataSource.fetchSeason(showId, seasonNumber)
}