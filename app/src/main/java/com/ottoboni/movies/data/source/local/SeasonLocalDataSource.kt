package com.ottoboni.movies.data.source.local

import com.ottoboni.movies.data.source.local.database.dao.SeasonDao
import com.ottoboni.movies.data.source.local.database.dao.SeasonWithEpisodesDao
import com.ottoboni.movies.domain.datasource.season.ISeasonLocalDataSource
import com.ottoboni.movies.domain.model.Season
import com.ottoboni.movies.domain.model.mapper.SeasonMapper
import com.ottoboni.movies.domain.model.mapper.SeasonWithEpisodesMapper

class SeasonLocalDataSource(
    private val seasonDao: SeasonDao,
    private val seasonWithEpisodesDao: SeasonWithEpisodesDao
) : ISeasonLocalDataSource {
    override suspend fun getBySeasonNumber(seasonNumber: Int): Season {
        return SeasonMapper.toDomain(seasonDao.getSeasonByNumber(seasonNumber))
    }

    override suspend fun save(domain: Season) {
        seasonDao.insert(SeasonMapper.fromDomain(domain))
    }

    override suspend fun saveAll(vararg domain: Season) {
        val seasonList = domain.map { SeasonMapper.fromDomain(it) }
        seasonDao.insert(*seasonList.toTypedArray())
    }

    override suspend fun update(domain: Season) {
        seasonDao.update(SeasonMapper.fromDomain(domain))
    }

    override suspend fun delete(domain: Season) {
        seasonDao.delete(SeasonMapper.fromDomain(domain))
    }

    override suspend fun getAll(): List<Season> {
        return seasonDao.getAll().map { SeasonMapper.toDomain(it) }
    }

    override suspend fun getById(id: Int): Season {
        return SeasonMapper.toDomain(seasonDao.getById(id))
    }

    override suspend fun getSeasonsWithEpisodesByShow(showId: Int): List<Season> {
        return seasonWithEpisodesDao.getSeasonsWithEpisodesByShow(showId).map {
            SeasonWithEpisodesMapper.toDomain(it)
        }
    }

    override suspend fun getSeasonWithEpisodesById(seasonId: Int): Season {
        return SeasonWithEpisodesMapper.toDomain(seasonWithEpisodesDao.getSeasonWithEpisodes(seasonId))
    }
}