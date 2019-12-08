package com.ottoboni.movies.data.repository

import com.ottoboni.movies.domain.datasource.season.ISeasonLocalDataSource
import com.ottoboni.movies.domain.datasource.season.ISeasonRemoteDataSource
import com.ottoboni.movies.domain.datasource.season.SeasonDataSource
import com.ottoboni.movies.domain.model.Season
import com.ottoboni.movies.domain.model.parser.SeasonParser

class SeasonRepository(
    private val localDataSource: ISeasonLocalDataSource,
    private val remoteDataSource: ISeasonRemoteDataSource
) : SeasonDataSource {
    override suspend fun getBySeasonNumber(seasonNumber: Int): Season {
        return localDataSource.getBySeasonNumber(seasonNumber)
    }

    override suspend fun getSeasonsWithEpisodes(showId: Int): List<Season> {
        return localDataSource.getSeasonsWithEpisodesByShow(showId)
    }

    override suspend fun getSeasonWithEpidosesById(seasonId: Int): Season {
        return localDataSource.getSeasonWithEpisodesById(seasonId)
    }

    override suspend fun save(domain: Season) {
        localDataSource.save(domain)
    }

    override suspend fun update(domain: Season) {
        localDataSource.update(domain)
    }

    override suspend fun delete(domain: Season) {
        localDataSource.delete(domain)
    }

    override suspend fun getAll(): List<Season> {
        return localDataSource.getAll()
    }

    override suspend fun getById(id: Int): Season {
        return localDataSource.getById(id)
    }

    override suspend fun fetchSeasonByNumber(showId: Int, seasonNumber: Int): Season {
        val seasonResponse = remoteDataSource.fetchSeasonAsync(showId, seasonNumber).await()

        val season = SeasonParser.parse(seasonResponse).apply { this.showId = showId }

        localDataSource.save(season)

        return season
    }
}